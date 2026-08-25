package com.fxd927.mekanismgeneticengineering.client.gui.machine;

import com.fxd927.mekanismgeneticengineering.client.gui.element.progress.MGEGuiProgress;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityDropsReproducer;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiDropsReproducer extends GuiConfigurableTile<TileEntityDropsReproducer, MekanismTileContainer<TileEntityDropsReproducer>> {
    public GuiDropsReproducer(MekanismTileContainer<TileEntityDropsReproducer> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        titleLabelY = 4;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), 158, 25))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY_REDUCED_RATE, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY_REDUCED_RATE));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getActive));
        addRenderableWidget(new GuiChemicalGauge(() -> tile.inputTank, () -> tile.getChemicalTanks(null), GaugeType.STANDARD, this, 7, 4))
                .warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT));
        addRenderableWidget(new MGEGuiProgress(tile::getScaledProgress, ProgressType.LARGE_RIGHT, this, 40, 40).recipeViewerCategory(tile))
                .warning(WarningTracker.WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}