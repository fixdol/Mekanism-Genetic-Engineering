package com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.machine;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import com.fxd927.mekanismgeneticengineering.client.gui.element.progress.MGEGuiProgress;
import com.fxd927.mekanismgeneticengineering.client.recipe_viewer.jei.MGEHolderRecipeCategory;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.functions.ConstantPredicates;
import mekanism.api.recipes.PressurizedReactionRecipe;
import mekanism.client.gui.element.bar.GuiHorizontalPowerBar;
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
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChemicalToTriChemicalRecipeCategory extends MGEHolderRecipeCategory<ChemicalToTripleChemicalRecipe> {
    protected static final String INPUT = "input";
    protected static final String FIRST_OUTPUT = "firstOutput";
    protected static final String SECOND_OUTPUT = "secondOutput";
    protected static final String THIRD_OUTPUT = "thirdOutput";

    private final GuiGauge<?> inputGauge;
    private final GuiGauge<?> firstOutputGauge;
    private final GuiGauge<?> secondOutputGauge;
    private final GuiGauge<?> thirdOutputGauge;
    protected final MGEGuiProgress rightArrow;

    public ChemicalToTriChemicalRecipeCategory(IGuiHelper helper, IRecipeViewerRecipeType<ChemicalToTripleChemicalRecipe> recipeType) {
        super(helper, recipeType);
        inputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT), this, 7, 4));
        firstOutputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT_1), this, 83, 17));
        secondOutputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT_2), this, 106, 17));
        thirdOutputGauge = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT), this, 129, 17));
        addSlot(SlotType.INPUT, 8, 65).with(SlotOverlay.MINUS);
        addSlot(SlotType.POWER, 153, 7).with(SlotOverlay.POWER);
        rightArrow = addConstantProgress(ProgressType.LARGE_RIGHT, 30, 43);
        addElement(new GuiVerticalPowerBar(this, RecipeViewerUtils.FULL_BAR, 158, 28));
    }

    @Override
    protected void renderElements(RecipeHolder<ChemicalToTripleChemicalRecipe> recipeHolder, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, int x, int y) {
        super.renderElements(recipeHolder, recipeSlotsView, guiGraphics, x, y);
        if (recipeSlotsView.findSlotByName(FIRST_OUTPUT).isEmpty()) {
            firstOutputGauge.drawBarOverlay(guiGraphics);
        }
        if (recipeSlotsView.findSlotByName(SECOND_OUTPUT).isEmpty()) {
            firstOutputGauge.drawBarOverlay(guiGraphics);
        }
        if (recipeSlotsView.findSlotByName(THIRD_OUTPUT).isEmpty()) {
            thirdOutputGauge.drawBarOverlay(guiGraphics);
        }
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, RecipeHolder<ChemicalToTripleChemicalRecipe> recipeHolder, @NotNull IFocusGroup focusGroup) {
        ChemicalToTripleChemicalRecipe recipe = recipeHolder.value();
        initChemical(builder, RecipeIngredientRole.INPUT, inputGauge, recipe.getInput().getRepresentations())
                .setSlotName(INPUT);

        List<ChemicalStack> firstOutputs = new ArrayList<>();
        List<ChemicalStack> secondOutputs = new ArrayList<>();
        List<ChemicalStack> thirdOutputs = new ArrayList<>();
        for (ChemicalToTripleChemicalRecipe.TripleChemicalRecipeOutput output : recipe.getOutputDefinition()) {
            firstOutputs.add(output.firstChemical());
            secondOutputs.add(output.secondChemical());
            thirdOutputs.add(output.thirdChemical());
        }

        if (!firstOutputs.stream().allMatch(ConstantPredicates.CHEMICAL_EMPTY)) {
            initChemical(builder, RecipeIngredientRole.OUTPUT, firstOutputGauge, firstOutputs)
                    .setSlotName(FIRST_OUTPUT);
        }
        if (!secondOutputs.stream().allMatch(ConstantPredicates.CHEMICAL_EMPTY)) {
            initChemical(builder, RecipeIngredientRole.OUTPUT, secondOutputGauge, secondOutputs)
                    .setSlotName(SECOND_OUTPUT);
        }
        if (!thirdOutputs.stream().allMatch(ConstantPredicates.CHEMICAL_EMPTY)) {
            initChemical(builder, RecipeIngredientRole.OUTPUT, thirdOutputGauge, thirdOutputs)
                    .setSlotName(THIRD_OUTPUT);
        }
    }
}
