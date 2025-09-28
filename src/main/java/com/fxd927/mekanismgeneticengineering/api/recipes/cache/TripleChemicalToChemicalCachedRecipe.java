package com.fxd927.mekanismgeneticengineering.api.recipes.cache;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.TriChemicalToChemicalRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BooleanSupplier;

@NothingNullByDefault
public class TripleChemicalToChemicalCachedRecipe extends CachedRecipe<TriChemicalToChemicalRecipe> {
    private final IOutputHandler<ChemicalStack> outputHandler;
    private final IInputHandler<ChemicalStack> chemicalFirstInputHandler;
    private final IInputHandler<ChemicalStack> chemicalSecondInputHandler;
    private final IInputHandler<ChemicalStack> chemicalThirdInputHandler;

    private ChemicalStack firstChemicalRecipeInput = ChemicalStack.EMPTY;
    private ChemicalStack secondChemicalRecipeInput = ChemicalStack.EMPTY;
    private ChemicalStack thirdChemicalRecipeInput = ChemicalStack.EMPTY;

    @Nullable
    private ChemicalStack output;

    public TripleChemicalToChemicalCachedRecipe(TriChemicalToChemicalRecipe recipe, BooleanSupplier recheckAllErrors, IInputHandler<@NotNull ChemicalStack> chemicalFirstInputHandler,
                                                      IInputHandler<@NotNull ChemicalStack> chemicalSecondInputHandler, IInputHandler<@NotNull ChemicalStack> chemicalThirdInputHandler,
                                                      IOutputHandler<ChemicalStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.chemicalFirstInputHandler = Objects.requireNonNull(chemicalFirstInputHandler, "Chemical input handler cannot be null.");
        this.chemicalSecondInputHandler = Objects.requireNonNull(chemicalSecondInputHandler, "Chemical input handler cannot be null.");
        this.chemicalThirdInputHandler = Objects.requireNonNull(chemicalThirdInputHandler, "Chemical input handler cannot be null.");
        this.outputHandler = Objects.requireNonNull(outputHandler, "Output handler cannot be null.");
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (tracker.shouldContinueChecking()) {
            firstChemicalRecipeInput = chemicalFirstInputHandler.getRecipeInput(recipe.getFirstInput());
            if (firstChemicalRecipeInput.isEmpty()) {
                tracker.mismatchedRecipe();
                return;
            }

            secondChemicalRecipeInput = chemicalSecondInputHandler.getRecipeInput(recipe.getSecondInput());
            if (secondChemicalRecipeInput.isEmpty()) {
                tracker.mismatchedRecipe();
                return;
            }

            if (!recipe.getThirdInput().hasNoMatchingInstances()) {
                thirdChemicalRecipeInput = chemicalThirdInputHandler.getRecipeInput(recipe.getThirdInput());
                if (thirdChemicalRecipeInput.isEmpty()) {
                    tracker.mismatchedRecipe();
                    return;
                }
            } else {
                thirdChemicalRecipeInput = ChemicalStack.EMPTY;
            }

            chemicalFirstInputHandler.calculateOperationsCanSupport(tracker, firstChemicalRecipeInput);
            if (tracker.shouldContinueChecking()) {
                chemicalSecondInputHandler.calculateOperationsCanSupport(tracker, secondChemicalRecipeInput);
                if (tracker.shouldContinueChecking()) {
                    if (!thirdChemicalRecipeInput.isEmpty()) {
                        chemicalThirdInputHandler.calculateOperationsCanSupport(tracker, thirdChemicalRecipeInput);
                    }
                    if (tracker.shouldContinueChecking()) {
                        output = recipe.getOutput(firstChemicalRecipeInput, secondChemicalRecipeInput, thirdChemicalRecipeInput);
                        outputHandler.calculateOperationsCanSupport(tracker, output);
                    }
                }
            }
        }
    }

    @Override
    public boolean isInputValid() {
        ChemicalStack first = chemicalFirstInputHandler.getInput();
        if (first.isEmpty()) {
            return false;
        }
        ChemicalStack second = chemicalSecondInputHandler.getInput();
        if (second.isEmpty()) {
            return false;
        }

        if (!recipe.getThirdInput().hasNoMatchingInstances()) {
            ChemicalStack third = chemicalThirdInputHandler.getInput();
            if (third.isEmpty()) {
                return false;
            }
            return recipe.test(first, second, third);
        } else {
            return recipe.test(first, second, ChemicalStack.EMPTY);
        }
    }

    @Override
    protected void finishProcessing(int operations) {
        if (output != null && !firstChemicalRecipeInput.isEmpty() && !secondChemicalRecipeInput.isEmpty()) {
            chemicalFirstInputHandler.use(firstChemicalRecipeInput, operations);
            chemicalSecondInputHandler.use(secondChemicalRecipeInput, operations);
            if (!thirdChemicalRecipeInput.isEmpty()) {
                chemicalThirdInputHandler.use(thirdChemicalRecipeInput, operations);
            }
            outputHandler.handleOutput(output, operations);
        }
    }
}
