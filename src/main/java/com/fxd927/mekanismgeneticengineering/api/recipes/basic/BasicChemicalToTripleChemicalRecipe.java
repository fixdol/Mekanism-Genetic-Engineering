package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.PressurizedReactionRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.Contract;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BasicChemicalToTripleChemicalRecipe extends ChemicalToTripleChemicalRecipe implements IBasicTriChemicalOutput{
    private final RecipeType<ChemicalToTripleChemicalRecipe> recipeType;
    protected final ChemicalStackIngredient input;
    protected final ChemicalStack firstOutput;
    protected final ChemicalStack secondOutput;
    protected final ChemicalStack thirdOutput;

    /**
     * @param input     Input.
     * @param firstOutput First Chemical output.
     * @param secondOutput Second Chemical output.
     * @param thirdOutput        Third Chemical output.
     */
    public BasicChemicalToTripleChemicalRecipe(ChemicalStackIngredient input, ChemicalStack firstOutput, ChemicalStack secondOutput, ChemicalStack thirdOutput,
                                                     RecipeType<ChemicalToTripleChemicalRecipe> recipeType) {
        this.recipeType = Objects.requireNonNull(recipeType, "Recipe type cannot be null");
        this.input = Objects.requireNonNull(input, "Item input cannot be null.");
        // Java
        Objects.requireNonNull(firstOutput, "First output cannot be null.");
        Objects.requireNonNull(secondOutput, "Second output cannot be null.");
        if (firstOutput.isEmpty() || secondOutput.isEmpty()) {
            throw new IllegalArgumentException("At least one output must not be empty.");
        }
        this.firstOutput = firstOutput.copy();
        this.secondOutput = secondOutput.copy();
        this.thirdOutput = thirdOutput.copy();
    }

    @Override
    public final RecipeType<ChemicalToTripleChemicalRecipe> getType() {
        return recipeType;
    }


    @Override
    public ChemicalStackIngredient getInput() {
        return input;
    }


    @Override
    public boolean test(ChemicalStack chemicalStack) {
        return (input.test(chemicalStack));
    }

    @Override
    @Contract(value = "_ -> new", pure = true)
    public TripleChemicalRecipeOutput getOutput(ChemicalStack chemical) {
        return new TripleChemicalRecipeOutput(this.firstOutput.copy(), this.secondOutput.copy(), this.thirdOutput.copy());
    }
    @Override
    public List<TripleChemicalRecipeOutput> getOutputDefinition() {
        return Collections.singletonList(new TripleChemicalRecipeOutput(firstOutput, secondOutput, thirdOutput));
    }
    public ChemicalStack getFirstOutputRaw() {
        return firstOutput;
    }

    public ChemicalStack getSecondOutputRaw() {
        return secondOutput;
    }

    public ChemicalStack getThirdOutputRaw() {
        return thirdOutput;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicChemicalToTripleChemicalRecipe other = (BasicChemicalToTripleChemicalRecipe) o;
        return input.equals(other.input) && firstOutput.equals(other.firstOutput) && secondOutput.equals(other.secondOutput) && thirdOutput.equals(other.thirdOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, firstOutput, secondOutput, thirdOutput);
    }
}