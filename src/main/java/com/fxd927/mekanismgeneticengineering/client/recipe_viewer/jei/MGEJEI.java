package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei;

import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.machine.ChemicalToTriChemicalRecipeCategory;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.machine.ItemChemicalChemicalToChemicalRecipeCategory;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.type.MGERecipeViewerRecipeType;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import mekanism.client.gui.GuiMekanism;
import mekanism.client.gui.robit.GuiRobitRepair;
import mekanism.client.recipe_viewer.jei.CatalystRegistryHelper;
import mekanism.client.recipe_viewer.jei.JeiGhostIngredientHandler;
import mekanism.client.recipe_viewer.jei.JeiGuiElementHandler;
import mekanism.client.recipe_viewer.jei.MekanismSubtypeInterpreter;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.capabilities.Capabilities;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static mekanism.client.recipe_viewer.jei.MekanismJEI.shouldLoad;

@JeiPlugin
public class MGEJEI implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return MekanismGeneticEngineering.rl("jei_plugin");
    }
    private static final ISubtypeInterpreter<ItemStack> MGE_DATA_INTERPRETER = new MekanismSubtypeInterpreter();
    private static final Map<IRecipeViewerRecipeType<?>, RecipeType<?>> recipeTypeInstanceCache = new HashMap<>();

    public static void registerItemSubtypes(ISubtypeRegistration registry, Collection<? extends Holder<? extends ItemLike>> itemProviders) {
        for (Holder<? extends ItemLike> itemProvider : itemProviders) {
            ItemStack stack = new ItemStack(itemProvider.value());
            if (Capabilities.STRICT_ENERGY.hasCapability(stack) || Capabilities.CHEMICAL.hasCapability(stack) || Capabilities.FLUID.hasCapability(stack)) {
                registry.registerSubtypeInterpreter(stack.getItem(), MGE_DATA_INTERPRETER);
            }
        }
    }

    public static <TYPE> RecipeType<TYPE> recipeType(IRecipeViewerRecipeType<TYPE> recipeType) {
        if (recipeType.requiresHolder()) {
            throw new IllegalStateException("Basic recipe type requested for a recipe that uses holders");
        }
        return (RecipeType<TYPE>) genericRecipeType(recipeType);
    }

    public static <TYPE extends Recipe<?>> RecipeType<RecipeHolder<TYPE>> holderRecipeType(IRecipeViewerRecipeType<TYPE> recipeType) {
        if (!recipeType.requiresHolder()) {
            throw new IllegalStateException("Holder recipe type requested for a recipe that doesn't use holders");
        }
        return (RecipeType<RecipeHolder<TYPE>>) genericRecipeType(recipeType);
    }

    public static RecipeType<?>[] recipeType(IRecipeViewerRecipeType<?>... recipeTypes) {
        return Arrays.stream(recipeTypes).map(MGEJEI::genericRecipeType).toArray(RecipeType[]::new);
    }

    public static RecipeType<?> genericRecipeType(IRecipeViewerRecipeType<?> recipeType) {
        return recipeTypeInstanceCache.computeIfAbsent(recipeType, r -> {
            if (r.requiresHolder()) {
                return new RecipeType<>(r.id(), RecipeHolder.class);
            }
            return new RecipeType<>(r.id(), r.recipeClass());
        });
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        if (!shouldLoad()) {
            return;
        }
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new ItemChemicalChemicalToChemicalRecipeCategory(guiHelper, MGERecipeViewerRecipeType.NUCLEAR_ACID_CENTRIFUGE));
        registry.addRecipeCategories(new ChemicalToTriChemicalRecipeCategory(guiHelper, MGERecipeViewerRecipeType.GENE_ANALYSIS));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registry) {
        if (!shouldLoad()) {
            return;
        }
        registry.addGenericGuiContainerHandler(GuiMekanism.class, new MGEJeiGuiElementHandler(registry.getJeiHelpers().getIngredientManager()));
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull IRecipeCatalystRegistration registry) {
        CatalystRegistryHelper.register(registry, MGERecipeViewerRecipeType.NUCLEAR_ACID_CENTRIFUGE);
        CatalystRegistryHelper.register(registry, MGERecipeViewerRecipeType.GENE_ANALYSIS);
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registry) {
        if (!shouldLoad()) {
            return;
        }

        MGERecipeRegistryHelper.register(registry, MGERecipeViewerRecipeType.NUCLEAR_ACID_CENTRIFUGE, MGERecipeType.NUCLEAR_ACID_CENTRIFUGE);
        MGERecipeRegistryHelper.register(registry, MGERecipeViewerRecipeType.GENE_ANALYSIS, MGERecipeType.GENE_ANALYSIS);
    }
}
