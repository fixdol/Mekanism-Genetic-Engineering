package com.fxd927.mekanismgeneticengineering.common.recipe.serializer;

import com.fxd927.mekanismgeneticengineering.api.MGESerializationConstants;
import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.IBasicTriChemicalOutput;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mekanism.api.SerializationConstants;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.basic.IBasicChemicalOutput;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IIngredientCreator;
import mekanism.common.recipe.serializer.MekanismRecipeSerializer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record MGERecipeSerializer() {

    public static <RECIPE extends ItemChemicalChemicalToChemicalRecipe & IBasicChemicalOutput> MekanismRecipeSerializer<RECIPE>
    itemChemicalChemicalToChemical(Function4<ItemStackIngredient, ChemicalStackIngredient, ChemicalStackIngredient, ChemicalStack, RECIPE> factory, IIngredientCreator<Chemical, ChemicalStack, ChemicalStackIngredient> ingredientCreator,
                               MapCodec<ChemicalStack> stackCodec, StreamCodec<? super RegistryFriendlyByteBuf, ChemicalStack> stackStreamCodec) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStackIngredient.CODEC.fieldOf(SerializationConstants.ITEM_INPUT).forGetter(ItemChemicalChemicalToChemicalRecipe::getItemInput),
                ingredientCreator.codec().fieldOf(MGESerializationConstants.FIRST_INPUT).forGetter(ItemChemicalChemicalToChemicalRecipe::getFirstInput),
                ingredientCreator.codec().fieldOf(MGESerializationConstants.SECOND_INPUT).forGetter(ItemChemicalChemicalToChemicalRecipe::getSecondInput),
                stackCodec.fieldOf(SerializationConstants.OUTPUT).forGetter(IBasicChemicalOutput::getOutputRaw)
        ).apply(instance, factory)), StreamCodec.composite(
                ItemStackIngredient.STREAM_CODEC, ItemChemicalChemicalToChemicalRecipe::getItemInput,
                ingredientCreator.streamCodec(), ItemChemicalChemicalToChemicalRecipe::getFirstInput,
                ingredientCreator.streamCodec(), ItemChemicalChemicalToChemicalRecipe::getSecondInput,
                stackStreamCodec, IBasicChemicalOutput::getOutputRaw,
                factory
        ));
    }

    public static <RECIPE extends ChemicalToTripleChemicalRecipe & IBasicTriChemicalOutput> MekanismRecipeSerializer<RECIPE>
    chemicalToTriChemical(Function4<ChemicalStackIngredient, ChemicalStack, ChemicalStack, ChemicalStack, RECIPE> factory, IIngredientCreator<Chemical, ChemicalStack, ChemicalStackIngredient> ingredientCreator,
                                   MapCodec<ChemicalStack> stackCodec, StreamCodec<? super RegistryFriendlyByteBuf, ChemicalStack> stackStreamCodec) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder.mapCodec(instance -> instance.group(
                ChemicalStackIngredient.CODEC.fieldOf(SerializationConstants.INPUT).forGetter(ChemicalToTripleChemicalRecipe::getInput),
                stackCodec.fieldOf(MGESerializationConstants.FIRST_OUTPUT).forGetter(IBasicTriChemicalOutput::getFirstOutputRaw),
                stackCodec.fieldOf(MGESerializationConstants.SECOND_OUTPUT).forGetter(IBasicTriChemicalOutput::getSecondOutputRaw),
                ChemicalStack.CODEC.optionalFieldOf(MGESerializationConstants.THIRD_OUTPUT, ChemicalStack.EMPTY).forGetter(IBasicTriChemicalOutput::getThirdOutputRaw)
        ).apply(instance, factory)), StreamCodec.composite(
                ChemicalStackIngredient.STREAM_CODEC, ChemicalToTripleChemicalRecipe::getInput,
                stackStreamCodec, IBasicTriChemicalOutput::getFirstOutputRaw,
                stackStreamCodec, IBasicTriChemicalOutput::getSecondOutputRaw,
                ChemicalStack.OPTIONAL_STREAM_CODEC, IBasicTriChemicalOutput::getThirdOutputRaw,
                factory
        ));
    }
}
