package com.fxd927.mekanismgeneticengineering.api.recipes;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import mekanism.api.recipes.ChemicalChemicalToChemicalRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MGERecipeTypes {
    public static final ResourceLocation NAME_NUCLEAR_ACID_CENTRIFUGE = ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, "nuclear_acid_centrifuge");
    public static final DeferredHolder<RecipeType<?>, RecipeType<ItemChemicalChemicalToChemicalRecipe>> TYPE_NUCLEAR_ACID_CENTRIFUGE = DeferredHolder.create(Registries.RECIPE_TYPE, NAME_NUCLEAR_ACID_CENTRIFUGE);
    public static final ResourceLocation NAME_GENE_ANALYSIS = ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, "genetic_analysis");
    public static final DeferredHolder<RecipeType<?>, RecipeType<ChemicalToTripleChemicalRecipe>> TYPE_GENE_ANALYSIS = DeferredHolder.create(Registries.RECIPE_TYPE, NAME_GENE_ANALYSIS);
    public static final ResourceLocation NAME_PROJECTING = ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, "projecting");
    public static final DeferredHolder<RecipeType<?>, RecipeType<ChemicalChemicalToChemicalRecipe>> TYPE_PROJECTING = DeferredHolder.create(Registries.RECIPE_TYPE, NAME_PROJECTING);

}
