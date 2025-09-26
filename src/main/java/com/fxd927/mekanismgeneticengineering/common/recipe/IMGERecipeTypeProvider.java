package com.fxd927.mekanismgeneticengineering.common.recipe;

import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IMGERecipeTypeProvider<VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache>{
    default ResourceLocation getRegistryName() {
        return getRecipeType().getRegistryName();
    }

    MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE> getRecipeType();

    default INPUT_CACHE getInputCache() {
        return getRecipeType().getInputCache();
    }

    @NotNull
    default List<RecipeHolder<RECIPE>> getRecipes(@Nullable Level world) {
        return getRecipeType().getRecipes(world);
    }

    @NotNull
    default List<RecipeHolder<RECIPE>> getRecipes(RecipeManager recipeManager, @Nullable Level world) {
        return getRecipeType().getRecipes(recipeManager, world);
    }

    default Stream<RecipeHolder<RECIPE>> stream(@Nullable Level world) {
        return getRecipes(world).stream();
    }

    /**
     * Finds the first recipe that matches the given criteria, or null if no matching recipe is found. Prefer using the find recipe methods in {@link #getInputCache()}.
     */
    @Nullable
    default RECIPE findFirst(@Nullable Level world, Predicate<RECIPE> matchCriteria) {
        for (RecipeHolder<RECIPE> recipeRecipeHolder : getRecipes(world)) {
            RECIPE value = recipeRecipeHolder.value();
            if (matchCriteria.test(value)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Checks if this recipe type contains a recipe that matches the given criteria. Prefer using the contains recipe methods in {@link #getInputCache()}.
     */
    default boolean contains(@Nullable Level world, Predicate<RECIPE> matchCriteria) {
        for (RecipeHolder<RECIPE> holder : getRecipes(world)) {
            if (matchCriteria.test(holder.value())) {
                return true;
            }
        }
        return false;
    }
}
