package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei;

import com.fxd927.mekanismgeneticengineering.common.recipe.IMGERecipeTypeProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MGERecipeRegistryHelper {

    private MGERecipeRegistryHelper() {
    }

    public static <RECIPE extends MekanismRecipe<?>> void register(IRecipeRegistration registry, IRecipeViewerRecipeType<RECIPE> recipeType,
                                                                   IMGERecipeTypeProvider<?, RECIPE, ?> type) {
        registry.addRecipes(MGEJEI.holderRecipeType(recipeType), type.getRecipes(null));
    }

    public static <RECIPE> void register(IRecipeRegistration registry, IRecipeViewerRecipeType<RECIPE> recipeType, Map<ResourceLocation, RECIPE> recipes) {
        register(registry, recipeType, List.copyOf(recipes.values()));
    }

    public static <RECIPE> void register(IRecipeRegistration registry, IRecipeViewerRecipeType<RECIPE> recipeType, List<RECIPE> recipes) {
        registry.addRecipes(MGEJEI.recipeType(recipeType), recipes);
    }

    public static void addAnvilRecipes(IRecipeRegistration registry, Holder<Item> item, Function<Item, ItemStack[]> repairMaterials) {
        IVanillaRecipeFactory factory = registry.getVanillaRecipeFactory();
        //Based off of how JEI adds for Vanilla items
        ItemStack damaged2 = new ItemStack(item);
        damaged2.setDamageValue(damaged2.getMaxDamage() * 3 / 4);
        ItemStack damaged3 = new ItemStack(item);
        damaged3.setDamageValue(damaged3.getMaxDamage() * 2 / 4);
        //Two damaged items combine to undamaged
        registry.addRecipes(RecipeTypes.ANVIL, List.of(factory.createAnvilRecipe(damaged2, List.of(damaged2), List.of(damaged3))));
        ItemStack[] repairStacks = repairMaterials.apply(item.value());
        //Damaged item + the repair material
        if (repairStacks != null && repairStacks.length > 0) {
            //While this is damaged1 it is down here as we don't need to bother creating the reference if we don't have a repair material
            ItemStack damaged1 = new ItemStack(item);
            damaged1.setDamageValue(damaged1.getMaxDamage());
            registry.addRecipes(RecipeTypes.ANVIL, List.of(factory.createAnvilRecipe(damaged1, List.of(repairStacks), List.of(damaged2))));
        }
    }
}
