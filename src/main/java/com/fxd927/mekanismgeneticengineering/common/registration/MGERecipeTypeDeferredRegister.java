package com.fxd927.mekanismgeneticengineering.common.registration;

import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.MekanismDeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Function;

public class MGERecipeTypeDeferredRegister extends MekanismDeferredRegister<RecipeType<?>> {
    public MGERecipeTypeDeferredRegister(String modid) {
        super(Registries.RECIPE_TYPE, modid, MGERecipeTypeRegistryObject::new);
    }

    public <VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache>
    MGERecipeTypeRegistryObject<VANILLA_INPUT, RECIPE, INPUT_CACHE> registerMek(String name, Function<ResourceLocation, ? extends MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE>> func) {
        return (MGERecipeTypeRegistryObject<VANILLA_INPUT, RECIPE, INPUT_CACHE>) super.register(name, func);
    }
}
