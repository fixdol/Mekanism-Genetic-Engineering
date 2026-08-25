package com.fxd927.mekanismgeneticengineering.common.datagen.builders;

import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicDropsProductionRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public class DropsReproductionRecipeBuilder extends MekanismRecipeBuilder<DropsReproductionRecipeBuilder> {
    private final ChemicalStackIngredient input;
    private final ItemStack firstOutput;
    private final ItemStack secondOutput;
    private final ItemStack thirdOutput;
    private final double secondChance;
    private final double thirdChance;

    protected DropsReproductionRecipeBuilder(ChemicalStackIngredient input, ItemStack firstOutput, ItemStack secondOutput, double secondChance, ItemStack thirdOutput, double thirdChance) {
        this.input = input;
        this.firstOutput = firstOutput;
        this.secondOutput = secondOutput;
        this.thirdOutput = thirdOutput;
        this.secondChance = secondChance;
        this.thirdChance = thirdChance;
    }

    public static DropsReproductionRecipeBuilder drops_reproduction(ChemicalStackIngredient input, ItemStack firstOutput) {
        if (firstOutput.isEmpty()) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a non empty output.");
        }
        return new DropsReproductionRecipeBuilder(input, firstOutput, ItemStack.EMPTY, 0, ItemStack.EMPTY, 0);
    }

    public static DropsReproductionRecipeBuilder drops_reproduction(ChemicalStackIngredient input, ItemStack secondOutput, double secondChance) {
        if (secondOutput.isEmpty()) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a non empty second output.");
        }
        if (secondChance <= 0 || secondChance > 1) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a second output chance greater than zero and at most one.");
        } else if (secondChance == 1) {
            throw new IllegalArgumentException("Drops reproduction recipes with a single 100% change output should specify their output as the first output.");
        }
        return new DropsReproductionRecipeBuilder(input, ItemStack.EMPTY, secondOutput, secondChance, ItemStack.EMPTY, 0);
    }

    public static DropsReproductionRecipeBuilder drops_reproduction(ChemicalStackIngredient input, ItemStack firstOutput, ItemStack secondOutput, double secondChance) {
        if (firstOutput.isEmpty() || secondOutput.isEmpty()) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a non empty primary, and second output.");
        }
        if (secondChance <= 0 || secondChance > 1) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a second output chance greater than zero and at most one.");
        }
        return new DropsReproductionRecipeBuilder(input, firstOutput, secondOutput, secondChance, ItemStack.EMPTY, 0);
    }

    public static DropsReproductionRecipeBuilder drops_reproduction(ChemicalStackIngredient input, ItemStack firstOutput, ItemStack secondOutput, double secondChance, ItemStack thirdOutput, double thirdChance) {
        if (firstOutput.isEmpty() || secondOutput.isEmpty() || thirdOutput.isEmpty()) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a non empty primary, and second output.");
        }
        if (secondChance <= 0 || secondChance > 1 || thirdChance <= 0 || thirdChance > 1) {
            throw new IllegalArgumentException("This drops reproduction recipe requires a second output chance greater than zero and at most one.");
        }
        return new DropsReproductionRecipeBuilder(input, firstOutput, secondOutput, secondChance, thirdOutput, thirdChance);
    }

    @Override
    protected DropsReproductionRecipe asRecipe() {
        return new BasicDropsProductionRecipe(input, firstOutput, secondOutput, thirdOutput, secondChance, thirdChance);
    }
}
