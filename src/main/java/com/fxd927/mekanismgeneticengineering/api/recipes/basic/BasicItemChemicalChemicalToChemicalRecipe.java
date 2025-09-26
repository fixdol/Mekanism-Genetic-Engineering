package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.basic.IBasicChemicalOutput;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BasicItemChemicalChemicalToChemicalRecipe extends ItemChemicalChemicalToChemicalRecipe implements IBasicChemicalOutput {

    private final RecipeType<ItemChemicalChemicalToChemicalRecipe> recipeType;
    protected final ItemStackIngredient itemInput;
    protected final ChemicalStackIngredient firstChemicalInput;
    protected final ChemicalStackIngredient secondChemicalInput;
    protected final ChemicalStack output;

    /**
     * @param itemInput     Item input.
     * @param firstChemicalInput First Chemical input.
     * @param secondChemicalInput Second Chemical input.
     * @param output        Output.
     */
    public BasicItemChemicalChemicalToChemicalRecipe(ItemStackIngredient itemInput, ChemicalStackIngredient firstChemicalInput, ChemicalStackIngredient secondChemicalInput, ChemicalStack output,
                                                     RecipeType<ItemChemicalChemicalToChemicalRecipe> recipeType) {
        this.recipeType = Objects.requireNonNull(recipeType, "Recipe type cannot be null");
        this.itemInput = Objects.requireNonNull(itemInput, "Item input cannot be null.");
        this.firstChemicalInput = Objects.requireNonNull(firstChemicalInput, "First Chemical input cannot be null.");
        this.secondChemicalInput = Objects.requireNonNull(secondChemicalInput, "First Chemical input cannot be null.");
        Objects.requireNonNull(output, "Output cannot be null.");
        if (output.isEmpty()) {
            throw new IllegalArgumentException("Output cannot be empty.");
        }
        this.output = output.copy();
    }

    @Override
    public final RecipeType<ItemChemicalChemicalToChemicalRecipe> getType() {
        return recipeType;
    }


    @Override
    public ItemStackIngredient getItemInput() {
        return itemInput;
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
    public boolean test(ItemStack itemStack, ChemicalStack input1, ChemicalStack input2) {
        return (itemInput.test(itemStack) && firstChemicalInput.test(input1) && secondChemicalInput.test(input2)) || (itemInput.test(itemStack) && firstChemicalInput.test(input2) && secondChemicalInput.test(input1));
    }

    @Override
    public ChemicalStack getOutput(ItemStack inputItem, ChemicalStack firstChemicalInput, ChemicalStack secondChemicalInput) {
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
        BasicItemChemicalChemicalToChemicalRecipe other = (BasicItemChemicalChemicalToChemicalRecipe) o;
        return firstChemicalInput.equals(other.firstChemicalInput) && secondChemicalInput.equals(other.secondChemicalInput) && itemInput.equals(other.itemInput) && output.equals(other.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemInput, firstChemicalInput, secondChemicalInput, output);
    }
}
