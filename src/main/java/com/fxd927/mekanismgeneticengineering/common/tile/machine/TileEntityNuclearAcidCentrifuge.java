package com.fxd927.mekanismgeneticengineering.common.tile.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.cache.ItemChemicalChemicalToChemicalCachedRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input.SingleItemBiChemicalRecipeInput;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.type.MGERecipeViewerRecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.IMGETripleRecipeLookupHandler;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEBlocks;
import com.fxd927.mekanismgeneticengineering.common.tile.prefab.MGETileEntityProgressMachine;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.ILongInputHandler;
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
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.inventory.warning.WarningTracker;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.ConfigInfo;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.ChemicalSlotInfo;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TileEntityNuclearAcidCentrifuge extends MGETileEntityProgressMachine<ItemChemicalChemicalToChemicalRecipe> implements
        IMGETripleRecipeLookupHandler.ItemDoubleChemicalRecipeLookupHandler<ItemChemicalChemicalToChemicalRecipe> {
    public static final CachedRecipe.OperationTracker.RecipeError NOT_ENOUGH_ITEM_INPUT_ERROR = CachedRecipe.OperationTracker.RecipeError.create();
    public static final CachedRecipe.OperationTracker.RecipeError NOT_ENOUGH_CHEMICAL_INPUT_ERROR = CachedRecipe.OperationTracker.RecipeError.create();
    public static final CachedRecipe.OperationTracker.RecipeError NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR = CachedRecipe.OperationTracker.RecipeError.create();
    public static final CachedRecipe.OperationTracker.RecipeError NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR = CachedRecipe.OperationTracker.RecipeError.create();
    private static final List<CachedRecipe.OperationTracker.RecipeError> TRACKED_ERROR_TYPES = List.of(
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY,
            NOT_ENOUGH_ITEM_INPUT_ERROR,
            NOT_ENOUGH_CHEMICAL_INPUT_ERROR,
            NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR,
            NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR,
            CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT
    );
    public static final long MAX_CHEMICAL = 10L * FluidType.BUCKET_VOLUME;
    public static final int BASE_TICKS_REQUIRED = SharedConstants.TICKS_PER_SECOND;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getInputGas", "getInputGasCapacity", "getInputGasNeeded",
            "getFirstInputGasFilledPercentage"}, docPlaceholder = "gas input")
    public IChemicalTank firstInputGasTank;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getOutputGas", "getOutputGasCapacity", "getOutputGasNeeded",
            "getSecondOutputGasFilledPercentage"}, docPlaceholder = "gas input")
    public IChemicalTank secondInputGasTank;
    @WrappingComputerMethod(wrapper =SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getOutput", "getOutputCapacity", "getOutputNeeded",
            "getOutputFilledPercentage"}, docPlaceholder = "output (center) tank")
    public IChemicalTank outputTank;

    private long clientEnergyUsed = 0L;

    private final IOutputHandler<@NotNull ChemicalStack> outputHandler;
    private final IInputHandler<@NotNull ItemStack> itemInputHandler;
    private final ILongInputHandler<@NotNull ChemicalStack> gasFirstInputHandler;
    private final ILongInputHandler<@NotNull ChemicalStack> gasSecondInputHandler;

    private MachineEnergyContainer<TileEntityNuclearAcidCentrifuge> energyContainer;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getInputItem", docPlaceholder = "input slot")
    InputInventorySlot inputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output slot")
    ChemicalInventorySlot outputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getEnergyItem", docPlaceholder = "energy slot")
    EnergyInventorySlot energySlot;


    public TileEntityNuclearAcidCentrifuge(BlockPos pos, BlockState state) {
        super(MGEBlocks.NUCLEAR_ACID_CENTRIFUGE, pos, state, TRACKED_ERROR_TYPES, BASE_TICKS_REQUIRED);
        ConfigInfo itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.INPUT, new InventorySlotInfo(true, true, inputSlot));
            itemConfig.addSlotInfo(DataType.OUTPUT, new InventorySlotInfo(true, true, outputSlot));
            itemConfig.addSlotInfo(DataType.ENERGY, new InventorySlotInfo(true, true, energySlot));
        }

        ConfigInfo gasConfig = configComponent.getConfig(TransmissionType.CHEMICAL);
        if (gasConfig != null) {
            gasConfig.addSlotInfo(DataType.INPUT_1, new ChemicalSlotInfo(true, false, firstInputGasTank));
            gasConfig.addSlotInfo(DataType.INPUT_2, new ChemicalSlotInfo(true, false, secondInputGasTank));
            gasConfig.addSlotInfo(DataType.OUTPUT, new ChemicalSlotInfo(false, true, outputTank));
            gasConfig.addSlotInfo(DataType.INPUT_OUTPUT, new ChemicalSlotInfo(true, true, firstInputGasTank, secondInputGasTank, outputTank));
        }

        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM, TransmissionType.CHEMICAL)
                .setCanTankEject(tank -> tank == outputTank);

        itemInputHandler = InputHelper.getInputHandler(inputSlot, NOT_ENOUGH_ITEM_INPUT_ERROR);
        gasFirstInputHandler = InputHelper.getInputHandler(firstInputGasTank, NOT_ENOUGH_CHEMICAL_INPUT_ERROR);
        gasSecondInputHandler = InputHelper.getInputHandler(secondInputGasTank, NOT_ENOUGH_CHEMICAL_INPUT_ERROR);
        outputHandler = OutputHelper.getOutputHandler(outputTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener, IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(firstInputGasTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, gas -> containsRecipeBAC(inputSlot.getStack(), gas, secondInputGasTank.getStack()), this::containsRecipeB, recipeCacheListener));
        builder.addTank(secondInputGasTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, gas -> containsRecipeCAB(inputSlot.getStack(), firstInputGasTank.getStack(), gas), this::containsRecipeC, recipeCacheListener));
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
        builder.addSlot(inputSlot = InputInventorySlot.at(item -> containsRecipeABC(item, firstInputGasTank.getStack(), secondInputGasTank.getStack()), this::containsRecipeA,
                        recipeCacheListener, 54, 40))
                .tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, getWarningCheck(NOT_ENOUGH_ITEM_INPUT_ERROR)));
        builder.addSlot(outputSlot = ChemicalInventorySlot.drain(outputTank, listener, 154, 56));
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
    public IMGERecipeTypeProvider<SingleItemBiChemicalRecipeInput, ItemChemicalChemicalToChemicalRecipe, MGEInputRecipeCache.ItemChemicalChemical<ItemChemicalChemicalToChemicalRecipe>> getRecipeType() {
        return MGERecipeType.NUCLEAR_ACID_CENTRIFUGE;
    }

    @Override
    public IRecipeViewerRecipeType<ItemChemicalChemicalToChemicalRecipe> recipeViewerType() {
        return MGERecipeViewerRecipeType.NUCLEAR_ACID_CENTRIFUGE;
    }

    @Nullable
    @Override
    public ItemChemicalChemicalToChemicalRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(itemInputHandler, gasFirstInputHandler, gasSecondInputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<ItemChemicalChemicalToChemicalRecipe> createNewCachedRecipe(@NotNull ItemChemicalChemicalToChemicalRecipe recipe, int cacheIndex) {
        return new ItemChemicalChemicalToChemicalCachedRecipe(recipe, recheckAllRecipeErrors, itemInputHandler, gasFirstInputHandler, gasSecondInputHandler, outputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(this::setOperatingTicks)
                .setBaselineMaxOperations(this::getOperationsPerTick);
    }

    public MachineEnergyContainer<TileEntityNuclearAcidCentrifuge> getEnergyContainer() {
        return energyContainer;
    }
}
