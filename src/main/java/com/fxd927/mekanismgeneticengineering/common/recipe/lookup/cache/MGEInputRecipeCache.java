package com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache;

import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ChemicalChemicalToChemicalRecipe;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.EitherSideInputRecipeCache;
import mekanism.common.recipe.lookup.cache.SingleInputRecipeCache;
import mekanism.common.recipe.lookup.cache.type.ChemicalInputCache;
import mekanism.common.recipe.lookup.cache.type.ItemInputCache;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.TriPredicate;

import java.util.function.Function;
import java.util.function.Predicate;

public class MGEInputRecipeCache {
    public static class SingleChemical<RECIPE extends MekanismRecipe<?> & Predicate<ChemicalStack>>
            extends MGESingleInputRecipeCache<ChemicalStack, ChemicalStackIngredient, RECIPE, ChemicalInputCache<RECIPE>> {

        public SingleChemical(MGERecipeType<?, RECIPE, ?> recipeType, Function<RECIPE, ChemicalStackIngredient> inputExtractor) {
            super(recipeType, inputExtractor, new ChemicalInputCache<>());
        }
    }

    public static class EitherSideChemical<RECIPE extends ChemicalChemicalToChemicalRecipe>
            extends MGEEitherSideInputRecipeCache<ChemicalStack, ChemicalStackIngredient, RECIPE, ChemicalInputCache<RECIPE>> {

        public EitherSideChemical(MGERecipeType<?, RECIPE, ?> recipeType) {
            super(recipeType, ChemicalChemicalToChemicalRecipe::getLeftInput, ChemicalChemicalToChemicalRecipe::getRightInput, new ChemicalInputCache<>());
        }
    }

    public static class ItemChemicalChemical<RECIPE extends MekanismRecipe<?> & TriPredicate<ItemStack, ChemicalStack, ChemicalStack>> extends
            MGETripleInputRecipeCache<ItemStack, ItemStackIngredient, ChemicalStack, ChemicalStackIngredient, ChemicalStack, ChemicalStackIngredient, RECIPE, ItemInputCache<RECIPE>,
                    ChemicalInputCache<RECIPE>, ChemicalInputCache<RECIPE>> {

        public ItemChemicalChemical(MGERecipeType<?, RECIPE, ?> recipeType, Function<RECIPE, ItemStackIngredient> inputAExtractor,
                                    Function<RECIPE, ChemicalStackIngredient> inputBExtractor, Function<RECIPE, ChemicalStackIngredient> inputCExtractor) {
            super(recipeType, inputAExtractor, new ItemInputCache<>(), inputBExtractor, new ChemicalInputCache<>(), inputCExtractor, new ChemicalInputCache<>());
        }
    }

    public static class TriChemical<RECIPE extends MekanismRecipe<?> & TriPredicate<ChemicalStack, ChemicalStack, ChemicalStack>> extends
            MGETripleInputRecipeCache<ChemicalStack, ChemicalStackIngredient, ChemicalStack, ChemicalStackIngredient, ChemicalStack, ChemicalStackIngredient, RECIPE, ChemicalInputCache<RECIPE>,
                    ChemicalInputCache<RECIPE>, ChemicalInputCache<RECIPE>> {

        public TriChemical(MGERecipeType<?, RECIPE, ?> recipeType, Function<RECIPE, ChemicalStackIngredient> inputAExtractor,
                                    Function<RECIPE, ChemicalStackIngredient> inputBExtractor, Function<RECIPE, ChemicalStackIngredient> inputCExtractor) {
            super(recipeType, inputAExtractor, new ChemicalInputCache<>(), inputBExtractor, new ChemicalInputCache<>(), inputCExtractor, new ChemicalInputCache<>());
        }
    }
}
