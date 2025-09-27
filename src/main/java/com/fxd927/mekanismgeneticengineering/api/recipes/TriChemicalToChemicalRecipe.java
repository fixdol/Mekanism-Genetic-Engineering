package com.fxd927.mekanismgeneticengineering.api.recipes;

import com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input.TriChemicalRecipeInput;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.vanilla_input.BiChemicalRecipeInput;
import mekanism.api.recipes.vanilla_input.SingleChemicalRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.TriPredicate;
import org.jetbrains.annotations.Contract;

import java.util.List;
import java.util.function.Predicate;

@NothingNullByDefault
public abstract class TriChemicalToChemicalRecipe extends MekanismRecipe<TriChemicalRecipeInput> implements TriPredicate<ChemicalStack, ChemicalStack, ChemicalStack> {
    @Override
    public abstract boolean test(ChemicalStack input1, ChemicalStack input2, ChemicalStack input3);

    @Override
    public boolean matches(TriChemicalRecipeInput input, Level level) {
        //Don't match incomplete recipes or ones that don't match
        return !isIncomplete() && test(input.firstChemical(), input.secondChemical(), input.thirdChemical());
    }

    /**
     * Gets a new output based on the given inputs, the order of these inputs which one is {@code input1} and which one is {@code input2} does not matter.
     *
     * @param input1 Specific "left" input.
     * @param input2 Specific "right" input.
     *
     * @return New output.
     *
     * @apiNote While Mekanism does not currently make use of the inputs, it is important to support it and pass the proper value in case any addons define input based
     * outputs where things like NBT may be different.
     * @implNote The passed in inputs should <strong>NOT</strong> be modified.
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    public abstract ChemicalStack getOutput(ChemicalStack input1, ChemicalStack input2, ChemicalStack input3);

    /**
     * Gets the left input ingredient.
     */
    public abstract ChemicalStackIngredient getFirstInput();

    /**
     * Gets the right input ingredient.
     */
    public abstract ChemicalStackIngredient getSecondInput();

    public abstract ChemicalStackIngredient getThirdInput();

    /**
     * For JEI, gets the output representations to display.
     *
     * @return Representation of the output, <strong>MUST NOT</strong> be modified.
     */
    public abstract List<ChemicalStack> getOutputDefinition();

    @Override
    public boolean isIncomplete() {
        return getFirstInput().hasNoMatchingInstances() || getSecondInput().hasNoMatchingInstances() || getThirdInput().hasNoMatchingInstances();
    }

    @Override
    public void logMissingTags() {
        getFirstInput().logMissingTags();
        getSecondInput().logMissingTags();
        getThirdInput().logMissingTags();
    }
}
