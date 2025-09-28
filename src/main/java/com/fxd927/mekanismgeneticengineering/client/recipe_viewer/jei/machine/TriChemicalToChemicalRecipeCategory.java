package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.TriChemicalToChemicalRecipe;
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

public class TriChemicalToChemicalRecipeCategory  extends MGEHolderRecipeCategory<TriChemicalToChemicalRecipe> {
    protected static final String FIRST_INPUT = "firstInput";
    protected static final String SECOND_INPUT = "secondInput";
    protected static final String THIRD_INPUT = "thirdInput";
    protected static final String OUTPUT = "output";

    private final GuiGauge<?> firstInputGauge;
    private final GuiGauge<?> secondInputGauge;
    private final GuiGauge<?> thirdInputGauge;
    private final GuiGauge<?> outputGauge;
    protected final MGEGuiProgress rightArrow;

    public TriChemicalToChemicalRecipeCategory(IGuiHelper helper, IRecipeViewerRecipeType<TriChemicalToChemicalRecipe> recipeType) {
        super(helper, recipeType);
        firstInputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT_1), this, 5, 15));
        secondInputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT_2), this, 28, 15));
        thirdInputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT), this, 51, 15));
        outputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT), this, 133, 13));
        addSlot(SlotType.OUTPUT, 154, 56).with(SlotOverlay.PLUS);
        addSlot(SlotType.POWER, 154, 14).with(SlotOverlay.POWER);
        rightArrow = addConstantProgress(ProgressType.LARGE_RIGHT, 77, 43);
        addElement(new GuiHorizontalPowerBar(this, RecipeViewerUtils.FULL_BAR, 115, 75));
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, RecipeHolder<TriChemicalToChemicalRecipe> recipeHolder, @NotNull IFocusGroup focusGroup) {
        TriChemicalToChemicalRecipe recipe = recipeHolder.value();
        builder.setShapeless();
        initChemical(builder, RecipeIngredientRole.INPUT, firstInputGauge, recipe.getFirstInput().getRepresentations())
                .setSlotName(FIRST_INPUT);
        initChemical(builder, RecipeIngredientRole.INPUT, secondInputGauge, recipe.getSecondInput().getRepresentations())
                .setSlotName(SECOND_INPUT);
        initChemical(builder, RecipeIngredientRole.INPUT, thirdInputGauge, recipe.getThirdInput().getRepresentations())
                .setSlotName(THIRD_INPUT);
        initChemical(builder, RecipeIngredientRole.OUTPUT, outputGauge, recipe.getOutputDefinition())
                .setSlotName(OUTPUT);
    }
}
