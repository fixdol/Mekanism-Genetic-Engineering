package com.fxd927.mekanismgeneticengineering.common.config;

import mekanism.api.tier.ITier;
import mekanism.common.Mekanism;
import mekanism.common.config.IConfigTranslation;
import mekanism.common.config.MekanismConfigTranslations;
import mekanism.common.config.TranslationPreset;
import mekanism.common.tier.*;
import mekanism.common.util.text.TextUtils;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Locale;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public enum MGEConfigTranslations implements IConfigTranslation {
    ENERGY_USAGE_NUCLEAR_ACID_CENTRIFUGE(TranslationPreset.ENERGY_USAGE, "Nuclear Acid Centrifuge"),
    ENERGY_STORAGE_NUCLEAR_ACID_CENTRIFUGE(TranslationPreset.ENERGY_STORAGE, "Nuclear Acid Centrifuge");

    private final String key;
    private final String title;
    private final String tooltip;
    @Nullable
    private final String button;

    MGEConfigTranslations(TranslationPreset preset, String type) {
        this(preset.path(type), preset.title(type), preset.tooltip(type));
    }

    MGEConfigTranslations(TranslationPreset preset, String type, String tooltipSuffix) {
        this(preset.path(type), preset.title(type), preset.tooltip(type) + tooltipSuffix);
    }

    MGEConfigTranslations(String path, String title, String tooltip) {
        this(path, title, tooltip, false);
    }

    MGEConfigTranslations(String path, String title, String tooltip, boolean isSection) {
        this(path, title, tooltip, IConfigTranslation.getSectionTitle(title, isSection));
    }

    MGEConfigTranslations(String path, String title, String tooltip, @Nullable String button) {
        this.key = Util.makeDescriptionId("configuration", Mekanism.rl(path));
        this.title = title;
        this.tooltip = tooltip;
        this.button = button;
    }

    @NotNull
    @Override
    public String getTranslationKey() {
        return key;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String tooltip() {
        return tooltip;
    }

    @Nullable
    @Override
    public String button() {
        return button;
    }
}
