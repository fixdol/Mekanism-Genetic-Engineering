package com.fxd927.mekanismgeneticengineering.common.recipe;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeTypes;
import com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input.SingleItemBiChemicalRecipeInput;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.registration.MGERecipeTypeDeferredRegister;
import com.fxd927.mekanismgeneticengineering.common.registration.MGERecipeTypeRegistryObject;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.vanilla_input.SingleChemicalRecipeInput;
import mekanism.client.MekanismClient;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MGERecipeType <VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache>
        implements RecipeType<RECIPE>, IMGERecipeTypeProvider<VANILLA_INPUT, RECIPE, INPUT_CACHE>{
    public static final MGERecipeTypeDeferredRegister RECIPE_TYPES = new MGERecipeTypeDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final MGERecipeTypeRegistryObject<SingleItemBiChemicalRecipeInput, ItemChemicalChemicalToChemicalRecipe, MGEInputRecipeCache.ItemChemicalChemical<ItemChemicalChemicalToChemicalRecipe>> NUCLEAR_ACID_CENTRIFUGE =
            register(MGERecipeTypes.NAME_NUCLEAR_ACID_CENTRIFUGE, recipeType -> new MGEInputRecipeCache.ItemChemicalChemical<>(recipeType, ItemChemicalChemicalToChemicalRecipe::getItemInput, ItemChemicalChemicalToChemicalRecipe::getFirstInput, ItemChemicalChemicalToChemicalRecipe::getSecondInput));

    public static final MGERecipeTypeRegistryObject<SingleChemicalRecipeInput, ChemicalToTripleChemicalRecipe, MGEInputRecipeCache.SingleChemical<ChemicalToTripleChemicalRecipe>> GENE_ANALYSIS =
            register(MGERecipeTypes.NAME_GENE_ANALYSIS, recipeType -> new MGEInputRecipeCache.SingleChemical<>(recipeType, ChemicalToTripleChemicalRecipe::getInput));


    private static <VANILLA_INPUT extends RecipeInput, RECIPE extends MekanismRecipe<VANILLA_INPUT>, INPUT_CACHE extends IInputRecipeCache>
    MGERecipeTypeRegistryObject<VANILLA_INPUT, RECIPE, INPUT_CACHE> register(
            ResourceLocation name,
            Function<MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator
    ) {
        if (!MekanismGeneticEngineering.MODID.equals(name.getNamespace())) {
            throw new IllegalStateException("Name must be in " + MekanismGeneticEngineering.MODID + " namespace");
        }
        return RECIPE_TYPES.registerMek(name.getPath(), registryName -> new MGERecipeType<>(registryName, inputCacheCreator));
    }

    public static void clearCache() {
        for (Holder<RecipeType<?>> entry : RECIPE_TYPES.getEntries()) {
            if (entry.value() instanceof MGERecipeType<?, ?, ?> recipeType) {
                recipeType.clearCaches();
            }
        }
    }

    private List<RecipeHolder<RECIPE>> cachedRecipes = Collections.emptyList();
    private final ResourceLocation registryName;
    private final INPUT_CACHE inputCache;

    private MGERecipeType(ResourceLocation name, Function<MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        this.registryName = name;
        this.inputCache = inputCacheCreator.apply(this);
    }

    @Override
    public String toString() {
        return registryName.toString();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public MGERecipeType<VANILLA_INPUT, RECIPE, INPUT_CACHE> getRecipeType() {
        return this;
    }

    private void clearCaches() {
        cachedRecipes = Collections.emptyList();
        inputCache.clear();
    }

    @Override
    public INPUT_CACHE getInputCache() {
        return inputCache;
    }

    @Nullable
    private Level getLevel(@Nullable Level level) {
        if (level == null) {
            if (FMLEnvironment.dist.isClient()) {
                return MekanismClient.tryGetClientWorld();
            }
            return ServerLifecycleHooks.getCurrentServer().overworld();
        }
        return level;
    }

    @NotNull
    @Override
    public List<RecipeHolder<RECIPE>> getRecipes(@Nullable Level world) {
        world = getLevel(world);
        if (world == null) {
            return Collections.emptyList();
        }
        return getRecipes(world.getRecipeManager(), world);
    }

    @NotNull
    @Override
    public List<RecipeHolder<RECIPE>> getRecipes(RecipeManager recipeManager, @Nullable Level world) {
        if (cachedRecipes.isEmpty()) {
            List<RecipeHolder<RECIPE>> recipes = recipeManager.getAllRecipesFor(this);
            cachedRecipes = recipes.stream()
                    .filter(recipe -> !recipe.value().isIncomplete())
                    .toList();
        }
        return cachedRecipes;
    }

    @SuppressWarnings("unchecked")
    private RECIPE castRecipe(MekanismRecipe<?> o) {
        if (o.getType() != this) {
            throw new IllegalArgumentException("Wrong recipe type");
        }
        return (RECIPE) o;
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static <I extends RecipeInput, RECIPE_TYPE extends Recipe<I>> Optional<RecipeHolder<RECIPE_TYPE>> getRecipeFor(RecipeType<RECIPE_TYPE> recipeType, I input,
                                                                                                                          Level level) {
        return level.getRecipeManager().getRecipeFor(recipeType, input, level)
                .filter(recipe -> recipe.value().isSpecial() || !recipe.value().isIncomplete());
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static Optional<RecipeHolder<?>> byKey(Level level, ResourceLocation id) {
        return level.getRecipeManager().byKey(id)
                .filter(recipe -> recipe.value().isSpecial() || !recipe.value().isIncomplete());
    }
}
