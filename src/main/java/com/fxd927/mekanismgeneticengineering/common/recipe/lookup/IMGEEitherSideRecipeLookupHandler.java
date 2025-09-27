package com.fxd927.mekanismgeneticengineering.common.recipe.lookup;

import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEEitherSideInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ChemicalChemicalToChemicalRecipe;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.common.recipe.lookup.IEitherSideRecipeLookupHandler;
import mekanism.common.recipe.lookup.cache.EitherSideInputRecipeCache;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;

public interface IMGEEitherSideRecipeLookupHandler<INPUT, RECIPE extends MekanismRecipe<?> & BiPredicate<INPUT, INPUT>,
        INPUT_CACHE extends MGEEitherSideInputRecipeCache<INPUT, ?, RECIPE, ?>> extends IMGERecipeLookupHandler.IMGERecipeTypedLookupHandler<RECIPE, INPUT_CACHE> {

    /**
     * Checks if there is a matching recipe of type {@link #getRecipeType()} that has the given input.
     *
     * @param input Recipe input.
     *
     * @return {@code true} if there is a match, {@code false} if there isn't.
     */
    default boolean containsRecipe(INPUT input) {
        return getRecipeType().getInputCache().containsInput(getLevel(), input);
    }

    /**
     * Checks if there is a matching recipe of type {@link #getRecipeType()} that has the given inputs.
     *
     * @param inputA Recipe input a.
     * @param inputB Recipe input b.
     *
     * @return {@code true} if there is a match, {@code false} if there isn't.
     *
     * @apiNote See {@link EitherSideInputRecipeCache#containsInput(Level, Object, Object)} for more details about what order to pass the inputs.
     */
    default boolean containsRecipe(INPUT inputA, INPUT inputB) {
        return getRecipeType().getInputCache().containsInput(getLevel(), inputA, inputB);
    }

    /**
     * Finds the first recipe for the type of recipe we handle ({@link #getRecipeType()}) by looking up the given inputs against the recipe type's input cache.
     *
     * @param inputA Recipe input a.
     * @param inputB Recipe input b.
     *
     * @return Recipe matching the given inputs, or {@code null} if no recipe matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(INPUT inputA, INPUT inputB) {
        return getRecipeType().getInputCache().findFirstRecipe(getLevel(), inputA, inputB);
    }

    /**
     * Finds the first recipe for the type of recipe we handle ({@link #getRecipeType()}) by looking up the given inputs against the recipe type's input cache.
     *
     * @param inputAHandler Input handler to grab the first recipe input from.
     * @param inputBHandler Input handler to grab the second recipe input from.
     *
     * @return Recipe matching the given inputs, or {@code null} if no recipe matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(IInputHandler<INPUT> inputAHandler, IInputHandler<INPUT> inputBHandler) {
        return findFirstRecipe(inputAHandler.getInput(), inputBHandler.getInput());
    }

    /**
     * Helper interface to make the generics that we have to pass to {@link IEitherSideRecipeLookupHandler} not as messy.
     */
    interface EitherSideChemicalRecipeLookupHandler<RECIPE extends ChemicalChemicalToChemicalRecipe> extends
            IMGEEitherSideRecipeLookupHandler<ChemicalStack, RECIPE, MGEInputRecipeCache.EitherSideChemical<RECIPE>> {
    }
}