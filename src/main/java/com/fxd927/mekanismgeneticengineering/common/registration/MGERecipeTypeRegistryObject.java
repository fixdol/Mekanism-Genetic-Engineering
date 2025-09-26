package com.fxd927.mekanismgeneticengineering.common.registration;

import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.MekanismDeferredHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

public class MGERecipeTypeRegistryObject <VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache> extends
        MekanismDeferredHolder<RecipeType<?>, MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE>> implements IMGERecipeTypeProvider<VANILLA_INPUT, RECIPE, INPUT_CACHE> {
    public MGERecipeTypeRegistryObject(ResourceKey<RecipeType<?>> key) {
        super(key);
    }

    @Override
    public MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE> getRecipeType() {
        return value();
    }
}
