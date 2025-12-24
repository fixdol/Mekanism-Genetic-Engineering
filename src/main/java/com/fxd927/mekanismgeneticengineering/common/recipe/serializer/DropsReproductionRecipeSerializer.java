package com.fxd927.mekanismgeneticengineering.common.recipe.serializer;

import com.fxd927.mekanismgeneticengineering.api.MGESerializationConstants;
import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.basic.BasicDropsProductionRecipe;
import com.mojang.datafixers.util.Function6;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mekanism.api.SerializationConstants;
import mekanism.api.SerializerHelper;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.Optional;

public class DropsReproductionRecipeSerializer implements RecipeSerializer<BasicDropsProductionRecipe> {

    private final StreamCodec<RegistryFriendlyByteBuf, BasicDropsProductionRecipe> streamCodec;
    private final MapCodec<BasicDropsProductionRecipe> codec;

    public DropsReproductionRecipeSerializer(Function6<ChemicalStackIngredient, ItemStack, ItemStack, ItemStack, Double, Double, BasicDropsProductionRecipe> factory) {
        Codec<Double> chanceCodec = Codec.DOUBLE.validate(d ->
                d > 0 && d <= 1
                        ? DataResult.success(d)
                        : DataResult.error(() -> "Expected chance to be > 0 and <= 1, but got " + d)
        );

        MapCodec<Optional<ItemStack>> mainOutputField = ItemStack.CODEC.optionalFieldOf(MGESerializationConstants.FIRST_OUTPUT);
        RecordCodecBuilder<BasicDropsProductionRecipe, Optional<ItemStack>> secondOutputField =
                ItemStack.CODEC.optionalFieldOf(MGESerializationConstants.SECOND_OUTPUT).forGetter(BasicDropsProductionRecipe::getSecondaryOutputRaw);
        RecordCodecBuilder<BasicDropsProductionRecipe, Optional<ItemStack>> thirdOutputField =
                ItemStack.CODEC.optionalFieldOf(MGESerializationConstants.THIRD_OUTPUT).forGetter(BasicDropsProductionRecipe::getThirdOutputRaw);

        // 確率フィールド
        MapCodec<Optional<Double>> secondChanceField = chanceCodec.optionalFieldOf(MGESerializationConstants.SECOND_CHANCE);
        MapCodec<Optional<Double>> thirdChanceField = chanceCodec.optionalFieldOf(MGESerializationConstants.THIRD_CHANCE);

        // --- MapCodec ---
        this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ChemicalStackIngredient.CODEC.fieldOf(SerializationConstants.INPUT).forGetter(DropsReproductionRecipe::getInput),

                SerializerHelper.oneRequired(secondOutputField, mainOutputField, BasicDropsProductionRecipe::getFirstOutputRaw),

                secondOutputField,
                SerializerHelper.dependentOptionality(secondOutputField, secondChanceField, recipe -> {
                    double chance = recipe.getSecondChance();
                    return chance == 0 ? Optional.empty() : Optional.of(chance);
                }),

                thirdOutputField,
                SerializerHelper.dependentOptionality(thirdOutputField, thirdChanceField, recipe -> {
                    double chance = recipe.getThirdChance();
                    return chance == 0 ? Optional.empty() : Optional.of(chance);
                })
        ).apply(instance, (input, mainOutput, sec1, chance1, sec2, chance2) ->
                factory.apply(
                        input,
                        mainOutput.orElse(ItemStack.EMPTY),
                        sec1.orElse(ItemStack.EMPTY), sec2.orElse(ItemStack.EMPTY),
                        chance1.orElse(0D), chance2.orElse(0D)
                )
        ));

        // --- StreamCodec ---
        this.streamCodec = StreamCodec.composite(
                ChemicalStackIngredient.STREAM_CODEC, DropsReproductionRecipe::getInput,
                ItemStack.OPTIONAL_STREAM_CODEC, r -> r.getFirstOutputRaw().orElse(ItemStack.EMPTY),
                ItemStack.OPTIONAL_STREAM_CODEC, r -> r.getSecondaryOutputRaw().orElse(ItemStack.EMPTY),
                ItemStack.OPTIONAL_STREAM_CODEC, r -> r.getThirdOutputRaw().orElse(ItemStack.EMPTY),
                ByteBufCodecs.DOUBLE, DropsReproductionRecipe::getSecondChance,
                ByteBufCodecs.DOUBLE, DropsReproductionRecipe::getThirdChance,
                factory
        );
    }

    @Override
    public MapCodec<BasicDropsProductionRecipe> codec() {
        return codec;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, BasicDropsProductionRecipe> streamCodec() {
        return streamCodec;
    }
}
