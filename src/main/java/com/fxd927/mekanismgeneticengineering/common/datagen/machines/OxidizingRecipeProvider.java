package com.fxd927.mekanismgeneticengineering.common.datagen.machines;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEItems;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.builder.ItemStackToChemicalRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

@SuppressWarnings("removal")
public class OxidizingRecipeProvider {
    public static void buildRecipes(RecipeOutput recipeOutput) {
        String basePath = "oxidizing/";

        ItemStackToChemicalRecipeBuilder.oxidizing(
                IngredientCreatorAccess.item().from(Ingredient.of(MGEItems.COAL_TAR.get())),
                new ChemicalStack(MGEChemicals.PHENOL.get().getAsHolder(), 100)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "phenol"));
    }
}
