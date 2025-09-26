package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.type;

import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public record MGERVRecipeTypeWrapper <VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache>(
        ResourceLocation id, ItemLike item, Class<? extends RECIPE> recipeClass, IMGERecipeTypeProvider<VANILLA_INPUT, RECIPE, INPUT_CACHE> vanillaProvider,
        int xOffset, int yOffset, int width, int height, List<ItemLike> workstations
) implements IRecipeViewerRecipeType<RECIPE>, IMGERecipeTypeProvider<VANILLA_INPUT, RECIPE, INPUT_CACHE> {
    public MGERVRecipeTypeWrapper(IMGERecipeTypeProvider<VANILLA_INPUT, RECIPE, INPUT_CACHE> vanillaProvider, Class<? extends RECIPE> recipeClass,
                               int xOffset, int yOffset, int width, int height, ItemLike icon, ItemLike... altWorkstations) {
        this(vanillaProvider.getRegistryName(), icon, recipeClass, vanillaProvider, xOffset, yOffset, width, height, List.of(altWorkstations));
    }

    public MGERVRecipeTypeWrapper {
        if (workstations.isEmpty()) {
            workstations = List.of(item);
        } else {
            workstations = Stream.concat(Stream.of(item), workstations.stream()).toList();
        }
    }

    @Override
    public Component getTextComponent() {
        return TextComponentUtil.build(item);
    }

    @Override
    public boolean requiresHolder() {
        return true;
    }

    @Override
    public ItemStack iconStack() {
        return new ItemStack(item);
    }

    @Nullable
    @Override
    public ResourceLocation icon() {
        //Handled by the icon stack
        return null;
    }

    @Override
    public MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE> getRecipeType() {
        return vanillaProvider.getRecipeType();
    }
}
