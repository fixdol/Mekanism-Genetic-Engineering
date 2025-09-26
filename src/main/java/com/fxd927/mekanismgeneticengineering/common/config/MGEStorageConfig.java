package com.fxd927.mekanismgeneticengineering.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.MekanismConfigTranslations;
import mekanism.common.config.value.CachedLongValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class MGEStorageConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;

    public final CachedLongValue nuclearAcidCentrifuge;

    MGEStorageConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        nuclearAcidCentrifuge = CachedLongValue.definedMin(this, builder, MGEConfigTranslations.ENERGY_STORAGE_NUCLEAR_ACID_CENTRIFUGE, "nuclearAcidCentrifuge",
                80_000L, 1);

        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "mge-machine-storage";
    }


    @Override
    public String getTranslation() {
        return "MGE Storage Config";
    }

    @Override
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
