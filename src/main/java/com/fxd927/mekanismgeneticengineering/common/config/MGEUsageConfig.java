package com.fxd927.mekanismgeneticengineering.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class MGEUsageConfig extends BaseMekanismConfig {
    private final ModConfigSpec configSpec;

    public final CachedLongValue nuclearAcidCentrifuge;

    MGEUsageConfig(){
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        nuclearAcidCentrifuge = CachedLongValue.definePositive(this, builder, MGEConfigTranslations.ENERGY_USAGE_NUCLEAR_ACID_CENTRIFUGE, "nuclearAcidCentrifuge", 200L);

        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "mge-machine-usage";
    }


    @Override
    public String getTranslation() {
        return "MGE Usage Config";
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
