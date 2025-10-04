package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.MGEAPI;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeSerializers;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeTypes;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BasicStructuralRestorationSynthesisRecipe extends BasicTriChemicalToChemicalRecipe {
    private static final Holder<Item> STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE = DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MGEAPI.MGE_MODID, "trait_decoding_projector"));

    public BasicStructuralRestorationSynthesisRecipe(ChemicalStackIngredient firstInput, ChemicalStackIngredient secondInput, ChemicalStackIngredient thirdInput, ChemicalStack output) {
        super(firstInput, secondInput, thirdInput, output, MGERecipeTypes.TYPE_STRUCTURAL_RESTORATION_SYNTHESIS.value());
    }

    @Override
    public String getGroup() {
        return "structural_restoration_synthesis_machine";
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE);
    }

    @Override
    public RecipeSerializer<BasicStructuralRestorationSynthesisRecipe> getSerializer() {
        return MGERecipeSerializers.STRUCTURAL_RESTORATION_SYNTHESIS.get();
    }
}
