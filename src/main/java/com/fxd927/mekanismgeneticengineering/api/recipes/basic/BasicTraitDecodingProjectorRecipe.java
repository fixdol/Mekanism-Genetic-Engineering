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
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;

@NothingNullByDefault
public class BasicTraitDecodingProjectorRecipe extends BasicChemicalChemicalToChemicalRecipe {
    private static final Holder<Item> TRAIT_DECODING_PROJECTOR = DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MGEAPI.MGE_MODID, "trait_decoding_projector"));

    /**
     * @param leftInput  Left input.
     * @param rightInput Right input.
     * @param output     Output.
     *
     * @apiNote The order of the inputs does not matter.
     */
    public BasicTraitDecodingProjectorRecipe(ChemicalStackIngredient leftInput, ChemicalStackIngredient rightInput, ChemicalStack output) {
        super(leftInput, rightInput, output, MGERecipeTypes.TYPE_PROJECTING.value());
    }

    @Override
    public String getGroup() {
        return "trait_decoding_projector";
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(TRAIT_DECODING_PROJECTOR);
    }

    @Override
    public RecipeSerializer<BasicTraitDecodingProjectorRecipe> getSerializer() {
        return MGERecipeSerializers.PROJECTING.get();
    }
}
