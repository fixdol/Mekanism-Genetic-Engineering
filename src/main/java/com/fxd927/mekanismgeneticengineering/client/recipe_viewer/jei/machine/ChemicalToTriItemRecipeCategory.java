package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.MGEHolderRecipeCategory;
import mekanism.client.gui.element.GuiUpArrow;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.RecipeViewerUtils;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.util.text.TextUtils;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

public class ChemicalToTriItemRecipeCategory extends MGEHolderRecipeCategory<DropsReproductionRecipe> {

    private final GuiGauge<?> input;
    private final GuiSlot output;

    public ChemicalToTriItemRecipeCategory(IGuiHelper helper, IRecipeViewerRecipeType<DropsReproductionRecipe> recipeType) {
        super(helper, recipeType);
        addElement(new GuiUpArrow(this, 60, 38));
        input = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT), this, 7, 4));
        addSlot(SlotType.POWER, 56, 53).with(SlotOverlay.POWER);
        output = addSlot(SlotType.OUTPUT_WIDE, 112, 31);
        addElement(new GuiVerticalPowerBar(this, RecipeViewerUtils.FULL_BAR, 164, 15));
        addSimpleProgress(ProgressType.BAR, 78, 38);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<DropsReproductionRecipe> recipeHolder, IFocusGroup focuses) {
        super.createRecipeExtras(builder, recipeHolder, focuses);
        double secondChance = recipeHolder.value().getSecondChance();
        if (secondChance > 0) {
            builder.addText(TextUtils.getPercent(secondChance), output.getWidth() - 2, font().lineHeight)
                    .setPosition(getGuiLeft() + output.getRelativeX() + 1, getGuiTop() + output.getRelativeBottom() + 1)
                    .setTextAlignment(HorizontalAlignment.RIGHT)
                    .setColor(titleTextColor());
        }
        double thirdChance = recipeHolder.value().getThirdChance();
        if (thirdChance > 0) {
            builder.addText(TextUtils.getPercent(thirdChance), output.getWidth() - 2, font().lineHeight)
                    .setPosition(getGuiLeft() + output.getRelativeX() + 1, getGuiTop() + output.getRelativeBottom() + 1)
                    .setTextAlignment(HorizontalAlignment.RIGHT)
                    .setColor(titleTextColor());
        }
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, RecipeHolder<DropsReproductionRecipe> recipeHolder, @NotNull IFocusGroup focusGroup) {
        DropsReproductionRecipe recipe = recipeHolder.value();
        initChemical(builder, RecipeIngredientRole.INPUT, input, recipe.getInput().getRepresentations());
        initItem(builder, RecipeIngredientRole.OUTPUT, output.getX() + 4, output.getY() + 4, recipe.getFirstOutputDefinition());
        initItem(builder, RecipeIngredientRole.OUTPUT, output.getX() + 20, output.getY() + 4, recipe.getSecondOutputDefinition());
        initItem(builder, RecipeIngredientRole.OUTPUT, output.getX() + 36, output.getY() + 4, recipe.getThirdOutputDefinition());
    }
}