package com.fxd927.mekanismgeneticengineering.api.recipes.cache;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.PressurizedReactionRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BooleanSupplier;

@NothingNullByDefault
public class ChemicalToTripleChemicalCachedRecipe extends CachedRecipe<ChemicalToTripleChemicalRecipe> {
    private final IInputHandler<ChemicalStack> inputHandler;
    private final IOutputHandler<ChemicalToTripleChemicalRecipe.@NotNull TripleChemicalRecipeOutput> outputHandler;

    private ChemicalStack recipeInput = ChemicalStack.EMPTY;

    @Nullable
    private ChemicalToTripleChemicalRecipe.TripleChemicalRecipeOutput output;

    public ChemicalToTripleChemicalCachedRecipe(ChemicalToTripleChemicalRecipe recipe, BooleanSupplier recheckAllErrors, IInputHandler<@NotNull ChemicalStack> inputHandler,
                                                IOutputHandler<ChemicalToTripleChemicalRecipe.TripleChemicalRecipeOutput> outputHandler) {
        super(recipe, recheckAllErrors);
        this.inputHandler = Objects.requireNonNull(inputHandler, "Item input handler cannot be null.");
        this.outputHandler = Objects.requireNonNull(outputHandler, "Fluid input handler cannot be null.");
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (tracker.shouldContinueChecking()) {
            //TODO - 1.18: If they are empty that means that we either don't have any or don't have enough of the input (or it doesn't match)
            // how do we want to best check if we have some but just not enough for the recipe?
            // Also figure this out for our other recipes that have multiple inputs
            recipeInput = inputHandler.getRecipeInput(recipe.getInput());
            if (recipeInput.isEmpty()) {
                tracker.mismatchedRecipe();
            }
            else {
                if (tracker.shouldContinueChecking()) {
                    output = recipe.getOutput(recipeInput);
                    outputHandler.calculateOperationsCanSupport(tracker, output);
                }
            }
        }
    }

    @Override
    public boolean isInputValid() {
        ChemicalStack chemicalStack = inputHandler.getInput();
        if (chemicalStack.isEmpty()) {
            return false;
        }
        return !chemicalStack.isEmpty() && recipe.test(chemicalStack);
    }

    @Override
    protected void finishProcessing(int operations) {
        if (output != null && !recipeInput.isEmpty()) {
            inputHandler.use(recipeInput, operations);
            outputHandler.handleOutput(output, operations);
        }
    }
}
