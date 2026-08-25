package com.fxd927.mekanismgeneticengineering.common.datagen.machines;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.builder.ChemicalChemicalToChemicalRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;

@SuppressWarnings("removal")
public class ChemicalInfusingRecipeProvider {
    public static void buildRecipes(RecipeOutput output) {
        String base = "chemical_infusing/";

        ChemicalChemicalToChemicalRecipeBuilder.chemicalInfusing(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.METHANE, 1),
                IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.CHLORINE, 3),
                new ChemicalStack(MGEChemicals.CHLOROFORM.get(), 1)
        ).build(output, MekanismGeneticEngineering.rl(base + "chloroform"));

        ChemicalChemicalToChemicalRecipeBuilder.chemicalInfusing(
                IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.CARBON, 1),
                IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.WATER_VAPOR, 2),
                new ChemicalStack(MGEChemicals.METHANE.get(), 1)
        ).build(output, MekanismGeneticEngineering.rl(base + "methane"));
    }
}
