package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.MGEAPI;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeSerializers;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeTypes;
import mekanism.api.MekanismAPI;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipeSerializers;
import mekanism.api.recipes.MekanismRecipeTypes;
import mekanism.api.recipes.basic.BasicChemicalChemicalToChemicalRecipe;
import mekanism.api.recipes.basic.BasicChemicalInfuserRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;

@NothingNullByDefault
public class BasicNuclearAcidCentrifugeRecipe extends BasicItemChemicalChemicalToChemicalRecipe {

    private static final Holder<Item> NUCLEAR_ACID_CENTRIFUGE = DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MGEAPI.MGE_MODID, "nuclear_acid_centrifuge"));

    public BasicNuclearAcidCentrifugeRecipe(ItemStackIngredient itemStack, ChemicalStackIngredient firstChemicalInput, ChemicalStackIngredient secondChemicalInput, ChemicalStack output) {
        super(itemStack, firstChemicalInput, secondChemicalInput, output, MGERecipeTypes.TYPE_NUCLEAR_ACID_CENTRIFUGE.value());
    }

    @Override
    public String getGroup() {
        return "chemical_infuser";
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(NUCLEAR_ACID_CENTRIFUGE);
    }

    @Override
    public RecipeSerializer<BasicNuclearAcidCentrifugeRecipe> getSerializer() {
        return MGERecipeSerializers.NUCLEAR_ACID_CENTRIFUGE.get();
    }
}
