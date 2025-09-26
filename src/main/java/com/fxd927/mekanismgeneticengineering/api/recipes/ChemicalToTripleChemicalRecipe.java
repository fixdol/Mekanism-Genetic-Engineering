package com.fxd927.mekanismgeneticengineering.api.recipes;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.PressurizedReactionRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.vanilla_input.SingleChemicalRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.TriPredicate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@NothingNullByDefault
public abstract class ChemicalToTripleChemicalRecipe extends MekanismRecipe<SingleChemicalRecipeInput> implements Predicate<ChemicalStack> {
    @Override
    public abstract boolean test(ChemicalStack chemicalStack);

    @Override
    public boolean matches(SingleChemicalRecipeInput input, Level level) {
        //Don't match incomplete recipes or ones that don't match
        return !isIncomplete() && test(input.chemical());
    }

    /**
     * Gets the input ingredient.
     */
    public abstract ChemicalStackIngredient getInput();

    /**
     * For JEI, gets the output representations to display.
     *
     * @return Representation of the output, <strong>MUST NOT</strong> be modified.
     */
    public abstract List<TripleChemicalRecipeOutput> getOutputDefinition();



    /**
     * Gets a new output based on the given input.
     *
     * @param input Specific input.
     *
     * @return New output.
     *
     * @apiNote While Mekanism does not currently make use of the input, it is important to support it and pass the proper value in case any addons define input based
     * outputs where things like NBT may be different.
     * @implNote The passed in input should <strong>NOT</strong> be modified.
     */
    @Contract(value = "_ -> new", pure = true)
    public abstract TripleChemicalRecipeOutput getOutput(ChemicalStack input);

    @Override
    public boolean isIncomplete() {
        return getInput().hasNoMatchingInstances();
    }

    public record TripleChemicalRecipeOutput(
            @NotNull ChemicalStack firstChemical,
            @NotNull ChemicalStack secondChemical,
            @NotNull ChemicalStack thirdChemical
    ) {
        public TripleChemicalRecipeOutput {
            Objects.requireNonNull(firstChemical, "firstChemical cannot be null.");
            Objects.requireNonNull(secondChemical, "secondChemical cannot be null.");
            Objects.requireNonNull(thirdChemical, "thirdChemical cannot be null.");
            int emptyCount = 0;
            if (firstChemical.isEmpty()) emptyCount++;
            if (secondChemical.isEmpty()) emptyCount++;
            if (thirdChemical.isEmpty()) emptyCount++;
            if (emptyCount > 1) {
                throw new IllegalArgumentException("At least two chemicals must be present.");
            }
        }
    }
}
