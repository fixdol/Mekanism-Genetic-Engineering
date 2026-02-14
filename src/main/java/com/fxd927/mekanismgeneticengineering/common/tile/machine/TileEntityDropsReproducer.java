package com.fxd927.mekanismgeneticengineering.common.tile.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.cache.MGEOneInputCachedRecipe;
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
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.inventory.warning.WarningTracker;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.util.MekanismUtils;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

import static mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE;

public class TileEntityDropsReproducer extends MGETileEntityProgressMachine<DropsReproductionRecipe> implements IMGESingleRecipeLookupHandler.ChemicalRecipeLookupHandler<DropsReproductionRecipe> {
    public static final CachedRecipe.OperationTracker.RecipeError NOT_ENOUGH_CHEMICAL_INPUT_ERROR = CachedRecipe.OperationTracker.RecipeError.create();
    public static final CachedRecipe.OperationTracker.RecipeError NOT_ENOUGH_SPACE_SECOND_OUTPUT_ERROR = CachedRecipe.OperationTracker.RecipeError.create();
    private static final List<CachedRecipe.OperationTracker.RecipeError> TRACKED_ERROR_TYPES = List.of(
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT,
            NOT_ENOUGH_CHEMICAL_INPUT_ERROR,
            NOT_ENOUGH_OUTPUT_SPACE,
            NOT_ENOUGH_SPACE_SECOND_OUTPUT_ERROR,

            CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT
    );
    public static final int BASE_TICKS_REQUIRED = 10 * SharedConstants.TICKS_PER_SECOND;

    private final IOutputHandler<DropsReproductionRecipe.@NotNull ChanceOutput> outputHandler;
    private final IInputHandler<@NotNull ChemicalStack> inputHandler;

    public static final long MAX_CHEMICAL = 10L * FluidType.BUCKET_VOLUME;

    public IChemicalTank inputTank;

    private MachineEnergyContainer<TileEntityDropsReproducer> energyContainer;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getInput", docPlaceholder = "input slot")
    ChemicalInventorySlot inputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutput", docPlaceholder = "output slot")
    OutputInventorySlot firstOutputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutput", docPlaceholder = "output slot")
    OutputInventorySlot secondOutputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutput", docPlaceholder = "output slot")
    OutputInventorySlot thirdOutputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getEnergyItem", docPlaceholder = "energy slot")
    EnergyInventorySlot energySlot;

    public TileEntityDropsReproducer(BlockPos pos, BlockState state) {
        super(MGEBlocks.DROPS_REPRODUCER, pos, state, TRACKED_ERROR_TYPES, BASE_TICKS_REQUIRED);
        configComponent.setupItemIOConfig(Collections.singletonList(inputSlot), List.of(firstOutputSlot, secondOutputSlot, thirdOutputSlot), energySlot, false);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        configComponent.setupInputConfig(TransmissionType.CHEMICAL, inputTank);

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM);

        inputHandler = InputHelper.getInputHandler(inputTank, NOT_ENOUGH_CHEMICAL_INPUT_ERROR);
        outputHandler = MGEOutputHelper.getOutputHandler(firstOutputSlot, NOT_ENOUGH_OUTPUT_SPACE, secondOutputSlot, NOT_ENOUGH_OUTPUT_SPACE, thirdOutputSlot, NOT_ENOUGH_OUTPUT_SPACE);
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener, IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(inputTank = BasicChemicalTank.inputModern(MAX_CHEMICAL, this::containsRecipe, this::containsRecipe, recipeCacheListener));
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
        builder.addSlot(inputSlot = ChemicalInventorySlot.fill(inputTank, recipeCacheListener, 56, 17))
                .tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT)));
        builder.addSlot(firstOutputSlot = OutputInventorySlot.at(recipeCacheUnpauseListener, 100, 35));
        builder.addSlot(secondOutputSlot = OutputInventorySlot.at(recipeCacheUnpauseListener, 118, 35));
        builder.addSlot(thirdOutputSlot = OutputInventorySlot.at(recipeCacheUnpauseListener, 136, 35));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener, 56, 53));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        inputSlot.fillTank();
        recipeCacheLookupMonitor.updateAndProcess();
        return sendUpdatePacket;
    }

    @Override
    @NotNull
    public IMGERecipeTypeProvider<SingleChemicalRecipeInput, DropsReproductionRecipe, MGEInputRecipeCache.SingleChemical<DropsReproductionRecipe>> getRecipeType() {
        return MGERecipeType.DROPS_REPRODUCTION;
    }

    @Override
    public IRecipeViewerRecipeType<DropsReproductionRecipe> recipeViewerType() {
        return MGERecipeViewerRecipeType.DROPS_REPRODUCTION;
    }

    @Nullable
    @Override
    public DropsReproductionRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<DropsReproductionRecipe> createNewCachedRecipe(@NotNull DropsReproductionRecipe recipe, int cacheIndex) {
        return MGEOneInputCachedRecipe.dropsReproduction(recipe, recheckAllRecipeErrors, inputHandler, outputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(this::setOperatingTicks)
                .setBaselineMaxOperations(this::getOperationsPerTick);
    }

    public MachineEnergyContainer<TileEntityDropsReproducer> getEnergyContainer() {
        return energyContainer;
    }

    @Override
    public boolean isConfigurationDataCompatible(Block blockType) {
        //Allow exact match or factories of the same type (as we will just ignore the extra data)
        return super.isConfigurationDataCompatible(blockType) || MekanismUtils.isSameTypeFactory(getBlockHolder(), blockType);
    }

    //Methods relating to IComputerTile
    @ComputerMethod(methodDescription = ComputerConstants.DESCRIPTION_GET_ENERGY_USAGE)
    long getEnergyUsage() {
        return getActive() ? energyContainer.getEnergyPerTick() : 0;
    }
    //End methods IComputerTile
}
