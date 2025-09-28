package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.recipes.TriChemicalToChemicalRecipe;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.basic.IBasicChemicalOutput;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BasicTriChemicalToChemicalRecipe extends TriChemicalToChemicalRecipe implements IBasicChemicalOutput {
    private final RecipeType<TriChemicalToChemicalRecipe> recipeType;
    protected final ChemicalStackIngredient firstChemicalInput;
    protected final ChemicalStackIngredient secondChemicalInput;
    protected final ChemicalStackIngredient thirdChemicalInput;
    protected final ChemicalStack output;


    public BasicTriChemicalToChemicalRecipe(ChemicalStackIngredient firstChemicalInput, ChemicalStackIngredient secondChemicalInput, ChemicalStackIngredient thirdChemicalInput, ChemicalStack output,
                                            RecipeType<TriChemicalToChemicalRecipe> recipeType) {
        this.recipeType = Objects.requireNonNull(recipeType, "Recipe type cannot be null");
        this.firstChemicalInput = Objects.requireNonNull(firstChemicalInput, "Item input cannot be null.");
        this.secondChemicalInput = Objects.requireNonNull(secondChemicalInput, "First Chemical input cannot be null.");
        this.thirdChemicalInput = Objects.requireNonNull(thirdChemicalInput, "First Chemical input cannot be null.");
        Objects.requireNonNull(output, "Output cannot be null.");
        if (output.isEmpty()) {
            throw new IllegalArgumentException("Output cannot be empty.");
        }
        this.output = output.copy();
    }

    @Override
    public final RecipeType<TriChemicalToChemicalRecipe> getType() {
        return recipeType;
    }


    @Override
    public ChemicalStackIngredient getFirstInput() {
        return firstChemicalInput;
    }

    @Override
    public ChemicalStackIngredient getSecondInput() {
        return secondChemicalInput;
    }

    @Override
    public ChemicalStackIngredient getThirdInput() {
        return thirdChemicalInput;
    }

    @Override
    public boolean test(ChemicalStack input1, ChemicalStack input2, ChemicalStack input3) {
        if (thirdChemicalInput == null || thirdChemicalInput.ingredient().isEmpty()) {
            return firstChemicalInput.test(input1) && secondChemicalInput.test(input2)
                    || firstChemicalInput.test(input2) && secondChemicalInput.test(input1);
        }

        return (firstChemicalInput.test(input1) && secondChemicalInput.test(input2) && thirdChemicalInput.test(input3))
                || (firstChemicalInput.test(input1) && thirdChemicalInput.test(input2) && secondChemicalInput.test(input3))
                || (secondChemicalInput.test(input1) && firstChemicalInput.test(input2) && thirdChemicalInput.test(input3))
                || (secondChemicalInput.test(input1) && thirdChemicalInput.test(input2) && firstChemicalInput.test(input3))
                || (thirdChemicalInput.test(input1) && firstChemicalInput.test(input2) && secondChemicalInput.test(input3))
                || (thirdChemicalInput.test(input1) && secondChemicalInput.test(input2) && firstChemicalInput.test(input3));
    }

    @Override
    public ChemicalStack getOutput(ChemicalStack firstChemicalInput, ChemicalStack secondChemicalInput, ChemicalStack thirdChemicalInput) {
        return output.copy();
    }

    @Override
    public List<ChemicalStack> getOutputDefinition() {
        return Collections.singletonList(output);
    }

    public ChemicalStack getOutputRaw() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicTriChemicalToChemicalRecipe other = (BasicTriChemicalToChemicalRecipe) o;
        return firstChemicalInput.equals(other.firstChemicalInput) && secondChemicalInput.equals(other.secondChemicalInput) && thirdChemicalInput.equals(other.thirdChemicalInput) && output.equals(other.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstChemicalInput, secondChemicalInput, thirdChemicalInput, output);
    }
}
