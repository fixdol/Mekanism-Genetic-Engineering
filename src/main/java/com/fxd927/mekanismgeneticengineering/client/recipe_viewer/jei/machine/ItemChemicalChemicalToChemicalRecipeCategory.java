package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.client.gui.element.progress.MGEGuiProgress;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.MGEHolderRecipeCategory;
import mekanism.client.gui.element.bar.GuiHorizontalPowerBar;
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
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

public class ItemChemicalChemicalToChemicalRecipeCategory extends MGEHolderRecipeCategory<ItemChemicalChemicalToChemicalRecipe> {
    protected static final String LEFT_INPUT = "leftInput";
    protected static final String RIGHT_INPUT = "rightInput";
    protected static final String OUTPUT = "output";

    private final GuiSlot input;
    private final GuiGauge<?> leftInputGauge;
    private final GuiGauge<?> rightInputGauge;
    private final GuiGauge<?> outputGauge;
    protected final MGEGuiProgress rightArrow;

    public ItemChemicalChemicalToChemicalRecipeCategory(IGuiHelper helper, IRecipeViewerRecipeType<ItemChemicalChemicalToChemicalRecipe> recipeType) {
        super(helper, recipeType);
        input = addSlot(SlotType.INPUT, 54, 40);
        leftInputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT_1), this, 5, 15));
        rightInputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT_2), this, 28, 15));
        outputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT), this, 133, 13));
        addSlot(SlotType.OUTPUT, 154, 56).with(SlotOverlay.PLUS);
        addSlot(SlotType.POWER, 154, 14).with(SlotOverlay.POWER);
        rightArrow = addConstantProgress(ProgressType.LARGE_RIGHT, 77, 43);
        addElement(new GuiHorizontalPowerBar(this, RecipeViewerUtils.FULL_BAR, 115, 75));
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, RecipeHolder<ItemChemicalChemicalToChemicalRecipe> recipeHolder, @NotNull IFocusGroup focusGroup) {
        ItemChemicalChemicalToChemicalRecipe recipe = recipeHolder.value();
        builder.setShapeless();
        initItem(builder, RecipeIngredientRole.INPUT, input, recipe.getItemInput().getRepresentations());
        initChemical(builder, RecipeIngredientRole.INPUT, leftInputGauge, recipe.getFirstInput().getRepresentations())
                .setSlotName(LEFT_INPUT);
        initChemical(builder, RecipeIngredientRole.INPUT, rightInputGauge, recipe.getSecondInput().getRepresentations())
                .setSlotName(RIGHT_INPUT);
        initChemical(builder, RecipeIngredientRole.OUTPUT, outputGauge, recipe.getOutputDefinition())
                .setSlotName(OUTPUT);
    }
}
