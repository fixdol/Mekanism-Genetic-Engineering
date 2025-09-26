package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.MGEAPI;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeSerializers;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeTypes;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;

@NothingNullByDefault
public class BasicGeneAnalysisRecipe extends BasicChemicalToTripleChemicalRecipe {

    private static final Holder<Item> GENE_ANALYSIS = DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MGEAPI.MGE_MODID, "gene_analysis"));

    public BasicGeneAnalysisRecipe(ChemicalStackIngredient input, ChemicalStack firstOutput, ChemicalStack secondOutput, ChemicalStack thirdOutput) {
        super(input, firstOutput, secondOutput, thirdOutput, MGERecipeTypes.TYPE_GENE_ANALYSIS.get());
    }

    @Override
    public String getGroup() {
        return "gene_analyzer";
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(GENE_ANALYSIS);
    }

    @Override
    public RecipeSerializer<BasicGeneAnalysisRecipe> getSerializer() {
        return MGERecipeSerializers.GENE_ANALYSIS.get();
    }
}
