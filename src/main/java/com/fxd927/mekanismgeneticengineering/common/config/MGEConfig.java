package com.fxd927.mekanismgeneticengineering.common.config;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import mekanism.common.config.IMekanismConfig;
import mekanism.common.config.MekanismConfigHelper;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.event.config.ModConfigEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MGEConfig {

    private MGEConfig(){
    }

    private static final Map<IConfigSpec, IMekanismConfig> KNOWN_CONFIGS = new HashMap<>();
    public static final MGEStorageConfig mgeStorage = new MGEStorageConfig();
    public static final MGEUsageConfig mgeUsage = new MGEUsageConfig();

    public static void registerConfigs(ModContainer modContainer) {
        MekanismConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, mgeStorage);
        MekanismConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, mgeUsage);
    }

    public static void onConfigLoad(ModConfigEvent configEvent) {
        MekanismConfigHelper.onConfigLoad(configEvent, MekanismGeneticEngineering.MODID, KNOWN_CONFIGS);
    }

    public static Collection<IMekanismConfig> getConfigs() {
        return Collections.unmodifiableCollection(KNOWN_CONFIGS.values());
    }
}
