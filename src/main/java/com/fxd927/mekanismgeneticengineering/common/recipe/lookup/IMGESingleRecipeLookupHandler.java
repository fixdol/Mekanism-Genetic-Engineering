package com.fxd927.mekanismgeneticengineering.common.recipe.lookup;

import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGESingleInputRecipeCache;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface IMGESingleRecipeLookupHandler <INPUT, RECIPE extends MekanismRecipe<?> & Predicate<INPUT>, INPUT_CACHE extends MGESingleInputRecipeCache<INPUT, ?, RECIPE, ?>>
        extends IMGERecipeLookupHandler.IMGERecipeTypedLookupHandler<RECIPE, INPUT_CACHE> {
    default boolean containsRecipe(INPUT input) {
        return getRecipeType().getInputCache().containsInput(getLevel(), input);
    }

    /**
     * Finds the first recipe for the type of recipe we handle ({@link #getRecipeType()}) by looking up the given input against the recipe type's input cache.
     *
     * @param input Recipe input.
     *
     * @return Recipe matching the given input, or {@code null} if no recipe matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(INPUT input) {
        return getRecipeType().getInputCache().findFirstRecipe(getLevel(), input);
    }

    /**
     * Finds the first recipe for the type of recipe we handle ({@link #getRecipeType()}) by looking up the given input against the recipe type's input cache.
     *
     * @param inputHandler Input handler to grab the recipe input from.
     *
     * @return Recipe matching the given input, or {@code null} if no recipe matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(IInputHandler<INPUT> inputHandler) {
        return findFirstRecipe(inputHandler.getInput());
    }

    interface ChemicalRecipeLookupHandler<RECIPE extends MekanismRecipe<?> & Predicate<ChemicalStack>> extends IMGESingleRecipeLookupHandler<ChemicalStack, RECIPE, MGEInputRecipeCache.SingleChemical<RECIPE>> {
    }
}
