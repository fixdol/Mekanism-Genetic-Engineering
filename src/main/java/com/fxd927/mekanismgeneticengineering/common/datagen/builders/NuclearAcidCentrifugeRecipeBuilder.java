package com.fxd927.mekanismgeneticengineering.common.datagen.builders;

import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicDropsProductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicNuclearAcidCentrifugeRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

@NothingNullByDefault
public class NuclearAcidCentrifugeRecipeBuilder extends MekanismRecipeBuilder<NuclearAcidCentrifugeRecipeBuilder> {
    private final ChemicalStackIngredient firstInput;
    private final ChemicalStackIngredient secondInput;
    private final ItemStackIngredient input;
    private final ChemicalStack output;

    protected NuclearAcidCentrifugeRecipeBuilder(ChemicalStackIngredient firstInput, ChemicalStackIngredient secondInput, ItemStackIngredient input, ChemicalStack output) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.input = input;
        this.output = output;
    }

    public static NuclearAcidCentrifugeRecipeBuilder nuclear_acid_centrifuge(ChemicalStackIngredient firstInput, ChemicalStackIngredient secondInput, ItemStackIngredient input, ChemicalStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This centrifuge recipe requires a non empty output.");
        }
        return new NuclearAcidCentrifugeRecipeBuilder(firstInput, secondInput, input, output);
    }

    @Override
    protected ItemChemicalChemicalToChemicalRecipe asRecipe() {
        return new BasicNuclearAcidCentrifugeRecipe(input, secondInput, firstInput, output) {
        };
    }
}
