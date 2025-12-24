package com.fxd927.mekanismgeneticengineering.api.recipes.cache;

import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.functions.ConstantPredicates;
import mekanism.api.recipes.*;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipeHelper;
import mekanism.api.recipes.ingredients.InputIngredient;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.*;

@NothingNullByDefault
public class MGEOneInputCachedRecipe<INPUT, OUTPUT, RECIPE extends MekanismRecipe<?> & Predicate<INPUT>> extends CachedRecipe<RECIPE> {
    private final IInputHandler<INPUT> inputHandler;
    private final IOutputHandler<OUTPUT> outputHandler;
    private final Predicate<INPUT> inputEmptyCheck;
    private final Supplier<? extends InputIngredient<INPUT>> inputSupplier;
    private final Function<INPUT, OUTPUT> outputGetter;
    private final Predicate<OUTPUT> outputEmptyCheck;
    private final Consumer<INPUT> inputSetter;
    private final Consumer<OUTPUT> outputSetter;

    @Nullable
    private INPUT input;
    @Nullable
    private OUTPUT output;

    protected MGEOneInputCachedRecipe(RECIPE recipe, BooleanSupplier recheckAllErrors, IInputHandler<INPUT> inputHandler, IOutputHandler<OUTPUT> outputHandler,
                                   Supplier<? extends InputIngredient<INPUT>> inputSupplier, Function<INPUT, OUTPUT> outputGetter, Predicate<INPUT> inputEmptyCheck,
                                   Predicate<OUTPUT> outputEmptyCheck) {
        super(recipe, recheckAllErrors);
        this.inputHandler = Objects.requireNonNull(inputHandler, "Input handler cannot be null.");
        this.outputHandler = Objects.requireNonNull(outputHandler, "Output handler cannot be null.");
        this.inputSupplier = Objects.requireNonNull(inputSupplier, "Input ingredient supplier cannot be null.");
        this.outputGetter = Objects.requireNonNull(outputGetter, "Output getter cannot be null.");
        this.inputEmptyCheck = Objects.requireNonNull(inputEmptyCheck, "Input empty check cannot be null.");
        this.outputEmptyCheck = Objects.requireNonNull(outputEmptyCheck, "Output empty check cannot be null.");
        this.inputSetter = input -> this.input = input;
        this.outputSetter = output -> this.output = output;
    }


    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        CachedRecipeHelper.oneInputCalculateOperationsThisTick(tracker, inputHandler, inputSupplier, inputSetter, outputHandler, outputGetter, outputSetter, inputEmptyCheck);
    }

    @Override
    public boolean isInputValid() {
        INPUT input = inputHandler.getInput();
        return !inputEmptyCheck.test(input) && recipe.test(input);
    }

    @Override
    protected void finishProcessing(int operations) {
        if (input != null && output != null && !inputEmptyCheck.test(input) && !outputEmptyCheck.test(output)) {
            inputHandler.use(input, operations);
            outputHandler.handleOutput(output, operations);
        }
    }

    public static MGEOneInputCachedRecipe<@NotNull ChemicalStack, DropsReproductionRecipe.@NotNull ChanceOutput, DropsReproductionRecipe> dropsReproduction(DropsReproductionRecipe recipe, BooleanSupplier recheckAllErrors,
                                                                                                                      IInputHandler<@NotNull ChemicalStack> inputHandler, IOutputHandler<DropsReproductionRecipe.@NotNull ChanceOutput> outputHandler) {
        return new MGEOneInputCachedRecipe<>(recipe, recheckAllErrors, inputHandler, outputHandler, recipe::getInput, recipe::getOutput, ConstantPredicates.CHEMICAL_EMPTY,
                ConstantPredicates.alwaysFalse());
    }

    public static <RECIPE extends ChemicalToChemicalRecipe> MGEOneInputCachedRecipe<@NotNull ChemicalStack, @NotNull ChemicalStack, RECIPE> chemicalToChemical(
            RECIPE recipe, BooleanSupplier recheckAllErrors, IInputHandler<@NotNull ChemicalStack> inputHandler, IOutputHandler<@NotNull ChemicalStack> outputHandler) {
        return new MGEOneInputCachedRecipe<>(recipe, recheckAllErrors, inputHandler, outputHandler, recipe::getInput, recipe::getOutput, ConstantPredicates.CHEMICAL_EMPTY,
                ConstantPredicates.CHEMICAL_EMPTY);
    }
}
