package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeSerializers;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicGeneAnalysisRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicNuclearAcidCentrifugeRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicTraitDecodingProjectorRecipe;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.recipe.serializer.MGERecipeSerializer;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipeSerializers;
import mekanism.api.recipes.basic.*;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.Mekanism;
import mekanism.common.recipe.serializer.MekanismRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MGERecipeSerializerInternal {

    private MGERecipeSerializerInternal() {
    }

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MekanismGeneticEngineering.MODID);

    static {
        MGERecipeSerializers.NUCLEAR_ACID_CENTRIFUGE = RECIPE_SERIALIZERS.register("nuclear_acid_centrifuge", () -> MGERecipeSerializer.itemChemicalChemicalToChemical(BasicNuclearAcidCentrifugeRecipe::new, IngredientCreatorAccess.chemicalStack(), ChemicalStack.MAP_CODEC, ChemicalStack.STREAM_CODEC));
        MGERecipeSerializers.GENE_ANALYSIS = RECIPE_SERIALIZERS.register("gene_analysis", () -> MGERecipeSerializer.chemicalToTriChemical(BasicGeneAnalysisRecipe::new, IngredientCreatorAccess.chemicalStack(), ChemicalStack.MAP_CODEC, ChemicalStack.STREAM_CODEC));
        MGERecipeSerializers.PROJECTING = RECIPE_SERIALIZERS.register("projecting", () -> MGERecipeSerializer.chemicalChemicalToChemical(BasicTraitDecodingProjectorRecipe::new, IngredientCreatorAccess.chemicalStack(), ChemicalStack.MAP_CODEC, ChemicalStack.STREAM_CODEC));
    }
}