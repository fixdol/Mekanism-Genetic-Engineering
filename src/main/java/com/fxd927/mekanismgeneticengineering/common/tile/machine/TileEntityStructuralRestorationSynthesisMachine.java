package com.fxd927.mekanismgeneticengineering.common.tile.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.TriChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.cache.TripleChemicalToChemicalCachedRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input.TriChemicalRecipeInput;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.type.MGERecipeViewerRecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.IMGETripleRecipeLookupHandler;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEBlocks;
import com.fxd927.mekanismgeneticengineering.common.tile.prefab.MGETileEntityProgressMachine;
import mekanism.api.IContentsListener;
import mekanism.api.Upgrade;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.integration.computer.computercraft.ComputerConstants;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.container.sync.SyncableLong;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.ConfigInfo;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.ChemicalSlotInfo;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TileEntityStructuralRestorationSynthesisMachine extends MGETileEntityProgressMachine<TriChemicalToChemicalRecipe> implements
        IMGETripleRecipeLookupHandler.TriChemicalRecipeLookupHandler<TriChemicalToChemicalRecipe> {
    private static final List<CachedRecipe.OperationTracker.RecipeError> TRACKED_ERROR_TYPES = List.of(
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY_REDUCED_RATE,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_LEFT_INPUT,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_RIGHT_INPUT,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT
    );
    public static final long MAX_CHEMICAL = 10L * FluidType.BUCKET_VOLUME;
    public static final int BASE_TICKS_REQUIRED = SharedConstants.TICKS_PER_SECOND;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getLeftInput", "getLeftInputCapacity", "getLeftInputNeeded",
            "getLeftInputFilledPercentage"}, docPlaceholder = "left input tank")
    public IChemicalTank firstTank;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getRightInput", "getRightInputCapacity", "getRightInputNeeded",
            "getRightInputFilledPercentage"}, docPlaceholder = "right input tank")
    public IChemicalTank secondTank;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getRightInput", "getRightInputCapacity", "getRightInputNeeded",
            "getRightInputFilledPercentage"}, docPlaceholder = "right input tank")
    public IChemicalTank thirdTank;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getOutput", "getOutputCapacity", "getOutputNeeded",
            "getOutputFilledPercentage"}, docPlaceholder = "output (center) tank")
    public IChemicalTank outputTank;


    private long clientEnergyUsed = 0L;
    private int baselineMaxOperations = 1;

    private final IOutputHandler<@NotNull ChemicalStack> outputHandler;
    private final IInputHandler<@NotNull ChemicalStack> firstInputHandler;
    private final IInputHandler<@NotNull ChemicalStack> secondInputHandler;
    private final IInputHandler<@NotNull ChemicalStack> thirdInputHandler;

    private MachineEnergyContainer<TileEntityStructuralRestorationSynthesisMachine> energyContainer;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output item slot")
    ChemicalInventorySlot firstInputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output item slot")
    ChemicalInventorySlot secondInputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output item slot")
    ChemicalInventorySlot thirdInputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output item slot")
    ChemicalInventorySlot outputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getEnergyItem", docPlaceholder = "energy slot")
    EnergyInventorySlot energySlot;

    public TileEntityStructuralRestorationSynthesisMachine(BlockPos pos, BlockState state) {
        super(MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE, pos, state, TRACKED_ERROR_TYPES, BASE_TICKS_REQUIRED);

        ConfigInfo itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.OUTPUT, new InventorySlotInfo(true, true, outputSlot));

            itemConfig.addSlotInfo(DataType.ENERGY, new InventorySlotInfo(true, true, energySlot));
        }

        ConfigInfo gasConfig = configComponent.getConfig(TransmissionType.CHEMICAL);
        if (gasConfig != null) {
            gasConfig.addSlotInfo(DataType.INPUT_1, new ChemicalSlotInfo(true, false, firstTank));
            gasConfig.addSlotInfo(DataType.INPUT_2, new ChemicalSlotInfo(true, false, secondTank));
            gasConfig.addSlotInfo(DataType.INPUT, new ChemicalSlotInfo(true, false, thirdTank));
            gasConfig.addSlotInfo(DataType.OUTPUT, new ChemicalSlotInfo(false, true, outputTank));
            gasConfig.addSlotInfo(DataType.INPUT_OUTPUT, new ChemicalSlotInfo(true, true, firstTank, secondTank, thirdTank, outputTank));
        }

        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM, TransmissionType.CHEMICAL)
                .setCanTankEject(tank -> tank == outputTank);

        firstInputHandler = InputHelper.getInputHandler(firstTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT);
        secondInputHandler = InputHelper.getInputHandler(secondTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT);
        thirdInputHandler = InputHelper.getInputHandler(thirdTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT);
        outputHandler = OutputHelper.getOutputHandler(outputTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener, IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(firstTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, gas -> containsRecipeABC(gas, secondTank.getStack(), thirdTank.getStack()), this::containsRecipeA, recipeCacheListener));
        builder.addTank(secondTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, gas -> containsRecipeBAC(firstTank.getStack(), gas, thirdTank.getStack()), this::containsRecipeB, recipeCacheListener));
        builder.addTank(thirdTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, gas -> containsRecipeCAB(firstTank.getStack(), secondTank.getStack(), gas), this::containsRecipeC, recipeCacheListener));
        builder.addTank(outputTank = BasicChemicalTank.output(MAX_CHEMICAL, recipeCacheUnpauseListener));
        return builder.build();
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener, IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = MachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @NotNull
    @Override
    protected IInventorySlotHolder getInitialInventory(IContentsListener listener, IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(outputSlot = ChemicalInventorySlot.drain(outputTank, listener, 80, 65));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener, 154, 14));
        outputSlot.setSlotType(ContainerSlotType.OUTPUT);
        outputSlot.setSlotOverlay(SlotOverlay.PLUS);
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        outputSlot.drainTank();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @ComputerMethod(nameOverride = "getEnergyUsage", methodDescription = ComputerConstants.DESCRIPTION_GET_ENERGY_USAGE)
    public long getEnergyUsed() {
        return clientEnergyUsed;
    }

    @NotNull
    @Override
    public IMGERecipeTypeProvider<TriChemicalRecipeInput, TriChemicalToChemicalRecipe, MGEInputRecipeCache.TriChemical<TriChemicalToChemicalRecipe>> getRecipeType() {
        return MGERecipeType.STRUCTURAL_RESTORATION_SYNTHESIS;
    }

    @Override
    public IRecipeViewerRecipeType<TriChemicalToChemicalRecipe> recipeViewerType() {
        return MGERecipeViewerRecipeType.STRUCTURAL_RESTORATION_SYNTHESIS;
    }

    @Nullable
    @Override
    public TriChemicalToChemicalRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(firstInputHandler, secondInputHandler, thirdInputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<TriChemicalToChemicalRecipe> createNewCachedRecipe(@NotNull TriChemicalToChemicalRecipe recipe, int cacheIndex) {
        return new TripleChemicalToChemicalCachedRecipe(recipe, recheckAllRecipeErrors, firstInputHandler, secondInputHandler, thirdInputHandler, outputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setBaselineMaxOperations(() -> baselineMaxOperations)
                .setOnFinish(this::markForSave);
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == Upgrade.SPEED) {
            baselineMaxOperations = (int) Math.pow(2, upgradeComponent.getUpgrades(Upgrade.SPEED));
        }
    }

    public MachineEnergyContainer<TileEntityStructuralRestorationSynthesisMachine> getEnergyContainer() {
        return energyContainer;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableLong.create(this::getEnergyUsed, value -> clientEnergyUsed = value));
    }
}
