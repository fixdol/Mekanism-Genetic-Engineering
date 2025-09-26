package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei;

import com.mojang.serialization.Codec;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IRecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

public abstract class MGEHolderRecipeCategory<RECIPE extends Recipe<?>> extends MGEBaseRecipeCategory<RecipeHolder<RECIPE>> {

    protected MGEHolderRecipeCategory(IGuiHelper helper, IRecipeViewerRecipeType<RECIPE> recipeType) {
        super(helper, MGEJEI.holderRecipeType(recipeType), recipeType.getTextComponent(), createIcon(helper, recipeType), recipeType.xOffset(), recipeType.yOffset(), recipeType.width(), recipeType.height());
    }

    @NotNull
    @Override
    public ResourceLocation getRegistryName(RecipeHolder<RECIPE> recipe) {
        return recipe.id();
    }

    @Override
    public Codec<RecipeHolder<RECIPE>> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {
        return codecHelper.getRecipeHolderCodec();
    }
}
