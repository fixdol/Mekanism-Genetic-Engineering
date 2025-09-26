package com.fxd927.mekanismgeneticengineering.common.attachments.chemical;

import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.functions.ConstantPredicates;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.attachments.containers.ContainsRecipe;
import mekanism.common.attachments.containers.chemical.AttachedChemicals;
import mekanism.common.attachments.containers.chemical.ComponentBackedChemicalTank;
import mekanism.common.attachments.containers.creator.BaseContainerCreator;
import mekanism.common.attachments.containers.creator.IBasicContainerCreator;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.function.Predicate;

public class MGEChemicalTanksBuilder {
    public static MGEChemicalTanksBuilder builder() {
        return new MGEChemicalTanksBuilder();
    }

    protected final List<IBasicContainerCreator<? extends ComponentBackedChemicalTank>> tankCreators = new ArrayList<>();

    protected MGEChemicalTanksBuilder() {
    }

    public BaseContainerCreator<AttachedChemicals, ComponentBackedChemicalTank> build() {
        return new MGEChemicalTanksBuilder.BaseChemicalTankBuilder(tankCreators);
    }

    public <VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache> MGEChemicalTanksBuilder addBasic(long capacity,
                                                                                                                                                                  IMGERecipeTypeProvider<VANILLA_INPUT, RECIPE, INPUT_CACHE> recipeType, ContainsRecipe<INPUT_CACHE, ChemicalStack> containsRecipe) {
        return addBasic(capacity, chemical -> containsRecipe.check(recipeType.getInputCache(), null, chemical));
    }

    public MGEChemicalTanksBuilder addBasic(long capacity, Predicate<ChemicalStack> isValid) {
        return addBasic(() -> capacity, isValid);
    }

    public MGEChemicalTanksBuilder addBasic(LongSupplier capacity, Predicate<ChemicalStack> isValid) {
        return addTank((type, attachedTo, containerIndex) -> new ComponentBackedChemicalTank(attachedTo,
                containerIndex, ConstantPredicates.manualOnly(), ConstantPredicates.alwaysTrueBi(), isValid, MekanismConfig.general.chemicalItemFillRate, capacity, null));
    }

    public MGEChemicalTanksBuilder addBasic(long capacity) {
        return addBasic(() -> capacity);
    }

    public MGEChemicalTanksBuilder addBasic(LongSupplier capacity) {
        return addTank((type, attachedTo, containerIndex) -> new ComponentBackedChemicalTank(attachedTo,
                containerIndex, ConstantPredicates.manualOnly(), ConstantPredicates.alwaysTrueBi(), ConstantPredicates.alwaysTrue(),
                MekanismConfig.general.chemicalItemFillRate, capacity, null));
    }

    public MGEChemicalTanksBuilder addInternalStorage(LongSupplier rate, LongSupplier capacity, Predicate<ChemicalStack> isValid) {
        return addTank((type, attachedTo, containerIndex) -> new ComponentBackedChemicalTank(attachedTo,
                containerIndex, ConstantPredicates.notExternal(), ConstantPredicates.alwaysTrueBi(), isValid, rate, capacity, null));
    }

    public MGEChemicalTanksBuilder addTank(IBasicContainerCreator<? extends ComponentBackedChemicalTank> tank) {
        tankCreators.add(tank);
        return this;
    }

    private static class BaseChemicalTankBuilder extends BaseContainerCreator<AttachedChemicals, ComponentBackedChemicalTank> {

        public BaseChemicalTankBuilder(List<IBasicContainerCreator<? extends ComponentBackedChemicalTank>> creators) {
            super(creators);
        }

        @Override
        public AttachedChemicals initStorage(int containers) {
            return AttachedChemicals.create(containers);
        }
    }
}
