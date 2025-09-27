package com.fxd927.mekanismgeneticengineering.api.recipes;

import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicGeneAnalysisRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicNuclearAcidCentrifugeRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicTraitDecodingProjectorRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MGERecipeSerializers {
    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BasicNuclearAcidCentrifugeRecipe>> NUCLEAR_ACID_CENTRIFUGE;
    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BasicGeneAnalysisRecipe>> GENE_ANALYSIS;
    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BasicTraitDecodingProjectorRecipe>> PROJECTING;

}
