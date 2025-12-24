package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.type;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.TriChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEBlocks;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.recipes.ChemicalChemicalToChemicalRecipe;

@NothingNullByDefault
public class MGERecipeViewerRecipeType {

    private MGERecipeViewerRecipeType() {
    }

    public static final MGERVRecipeTypeWrapper<?, ItemChemicalChemicalToChemicalRecipe, ?> NUCLEAR_ACID_CENTRIFUGE = new MGERVRecipeTypeWrapper<>(MGERecipeType.NUCLEAR_ACID_CENTRIFUGE, ItemChemicalChemicalToChemicalRecipe.class, -3, -3, 170, 80, MGEBlocks.NUCLEAR_ACID_CENTRIFUGE);
    public static final MGERVRecipeTypeWrapper<?, ChemicalToTripleChemicalRecipe, ?> GENE_ANALYSIS = new MGERVRecipeTypeWrapper<>(MGERecipeType.GENE_ANALYSIS, ChemicalToTripleChemicalRecipe.class, -3, -3, 170, 80, MGEBlocks.GENE_ANALYZER);
    public static final MGERVRecipeTypeWrapper<?, TriChemicalToChemicalRecipe, ?> STRUCTURAL_RESTORATION_SYNTHESIS = new MGERVRecipeTypeWrapper<>(MGERecipeType.STRUCTURAL_RESTORATION_SYNTHESIS, TriChemicalToChemicalRecipe.class, -3, -3, 170, 80, MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE);
    public static final MGERVRecipeTypeWrapper<?, DropsReproductionRecipe, ?> DROPS_REPRODUCTION = new MGERVRecipeTypeWrapper<>(MGERecipeType.DROPS_REPRODUCTION, DropsReproductionRecipe.class, -3, -3, 170, 80, MGEBlocks.DROPS_REPRODUCER);
}
