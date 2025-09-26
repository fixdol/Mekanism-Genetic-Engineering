package com.fxd927.mekanismgeneticengineering.common.tile.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.cache.ChemicalToTripleChemicalCachedRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.outputs.MGEOutputHelper;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.type.MGERecipeViewerRecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.IMGESingleRecipeLookupHandler;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEBlocks;
import com.fxd927.mekanismgeneticengineering.common.tile.prefab.MGETileEntityProgressMachine;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.api.recipes.vanilla_input.SingleChemicalRecipeInput;
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
import mekanism.common.inventory.container.slot.SlotOverlay;
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

public class TileEntityGeneAnalyzer extends MGETileEntityProgressMachine<ChemicalToTripleChemicalRecipe> implements
        IMGESingleRecipeLookupHandler.ChemicalRecipeLookupHandler<ChemicalToTripleChemicalRecipe> {

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
    public IChemicalTank inputGasTank;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getInputGas", "getInputGasCapacity", "getInputGasNeeded",
            "getFirstInputGasFilledPercentage"}, docPlaceholder = "gas input")
    public IChemicalTank firstOutputGasTank;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getOutputGas", "getOutputGasCapacity", "getOutputGasNeeded",
            "getSecondOutputGasFilledPercentage"}, docPlaceholder = "gas input")
    public IChemicalTank secondOutputGasTank;
    @WrappingComputerMethod(wrapper =SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getOutput", "getOutputCapacity", "getOutputNeeded",
            "getOutputFilledPercentage"}, docPlaceholder = "output (center) tank")
    public IChemicalTank thirdOutputTank;

    private long clientEnergyUsed = 0L;


    private final IInputHandler<@NotNull ChemicalStack> inputHandler;
    private final IOutputHandler<ChemicalToTripleChemicalRecipe.@NotNull TripleChemicalRecipeOutput> outputHandler;

    private MachineEnergyContainer<TileEntityGeneAnalyzer> energyContainer;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output slot")
    ChemicalInventorySlot inputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getEnergyItem", docPlaceholder = "energy slot")
    EnergyInventorySlot energySlot;

    public TileEntityGeneAnalyzer(BlockPos pos, BlockState state) {
        super(MGEBlocks.GENE_ANALYZER, pos, state, TRACKED_ERROR_TYPES, BASE_TICKS_REQUIRED);
        ConfigInfo itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.INPUT, new InventorySlotInfo(true, true, inputSlot));
            itemConfig.addSlotInfo(DataType.ENERGY, new InventorySlotInfo(true, true, energySlot));
        }

        ConfigInfo gasConfig = configComponent.getConfig(TransmissionType.CHEMICAL);
        if (gasConfig != null) {
            gasConfig.addSlotInfo(DataType.INPUT, new ChemicalSlotInfo(true, false, inputGasTank));
            gasConfig.addSlotInfo(DataType.OUTPUT_1, new ChemicalSlotInfo(false, true, firstOutputGasTank));
            gasConfig.addSlotInfo(DataType.OUTPUT_2, new ChemicalSlotInfo(false, true, secondOutputGasTank));
            gasConfig.addSlotInfo(DataType.OUTPUT, new ChemicalSlotInfo(false, true, thirdOutputTank));
            gasConfig.addSlotInfo(DataType.INPUT_OUTPUT, new ChemicalSlotInfo(true, true, inputGasTank, firstOutputGasTank, secondOutputGasTank, thirdOutputTank));
        }

        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM, TransmissionType.CHEMICAL)
                .setCanTankEject(tank -> tank != inputGasTank);

        inputHandler = InputHelper.getInputHandler(inputGasTank, NOT_ENOUGH_CHEMICAL_INPUT_ERROR);
        outputHandler = MGEOutputHelper.getOutputHandler(firstOutputGasTank, NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR, secondOutputGasTank, NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR, thirdOutputTank, NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR
        );
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener, IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(inputGasTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, gas -> containsRecipe(gas), this::containsRecipe, recipeCacheListener));
        builder.addTank(firstOutputGasTank = BasicChemicalTank.output(MAX_CHEMICAL, recipeCacheUnpauseListener));
        builder.addTank(secondOutputGasTank = BasicChemicalTank.output(MAX_CHEMICAL, recipeCacheUnpauseListener));
        builder.addTank(thirdOutputTank = BasicChemicalTank.output(MAX_CHEMICAL, recipeCacheUnpauseListener));
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
        builder.addSlot(inputSlot = ChemicalInventorySlot.fill(inputGasTank, listener, 8, 65));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener, 153, 7));
        inputSlot.setSlotOverlay(SlotOverlay.MINUS);
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        inputSlot.fillTank();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @ComputerMethod(nameOverride = "getEnergyUsage", methodDescription = ComputerConstants.DESCRIPTION_GET_ENERGY_USAGE)
    public long getEnergyUsed() {
        return clientEnergyUsed;
    }

    @NotNull
    @Override
    public IMGERecipeTypeProvider<SingleChemicalRecipeInput, ChemicalToTripleChemicalRecipe, MGEInputRecipeCache.SingleChemical<ChemicalToTripleChemicalRecipe>> getRecipeType() {
        return MGERecipeType.GENE_ANALYSIS;
    }

    @Override
    public IRecipeViewerRecipeType<ChemicalToTripleChemicalRecipe> recipeViewerType() {
        return MGERecipeViewerRecipeType.GENE_ANALYSIS;
    }

    @Nullable
    @Override
    public ChemicalToTripleChemicalRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<ChemicalToTripleChemicalRecipe> createNewCachedRecipe(@NotNull ChemicalToTripleChemicalRecipe recipe, int cacheIndex) {
        return new ChemicalToTripleChemicalCachedRecipe(recipe, recheckAllRecipeErrors, inputHandler, outputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(this::setOperatingTicks)
                .setBaselineMaxOperations(this::getOperationsPerTick);
    }

    public MachineEnergyContainer<TileEntityGeneAnalyzer> getEnergyContainer() {
        return energyContainer;
    }

}
