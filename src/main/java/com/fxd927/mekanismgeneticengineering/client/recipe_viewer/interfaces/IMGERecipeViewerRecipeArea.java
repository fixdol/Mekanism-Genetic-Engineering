package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.interfaces;

import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.IMGERecipeLookupHandler;
import mekanism.client.gui.element.GuiElement;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import net.minecraft.client.gui.components.events.GuiEventListener;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public interface IMGERecipeViewerRecipeArea <ELEMENT extends GuiElement> extends GuiEventListener {
    @Nullable
    IRecipeViewerRecipeType<?>[] getRecipeCategories();

    default boolean isRecipeViewerAreaActive() {
        return true;
    }

    ELEMENT recipeViewerCategories(@NotNull IRecipeViewerRecipeType<?>... recipeCategories);

    default ELEMENT recipeViewerCategory(IMGERecipeLookupHandler<?> recipeLookup) {
        IRecipeViewerRecipeType<?> recipeType = recipeLookup.recipeViewerType();
        if (recipeType != null) {
            return recipeViewerCategories(recipeType);
        }
        return (ELEMENT) this;
    }

    default ELEMENT recipeViewerCrafting() {
        return recipeViewerCategories(RecipeViewerRecipeType.VANILLA_CRAFTING);
    }

    default boolean isMouseOverRecipeViewerArea(double mouseX, double mouseY) {
        return isMouseOver(mouseX, mouseY);
    }

}
