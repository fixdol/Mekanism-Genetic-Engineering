package com.fxd927.mekanismgeneticengineering.common;

import com.fxd927.mekanismgeneticengineering.common.config.MGEConfig;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.registries.*;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(MekanismGeneticEngineering.MODID)
public class MekanismGeneticEngineering
{
    public static final String MODID = "mekanismgeneticengineering";

    public MekanismGeneticEngineering(ModContainer modContainer, IEventBus modEventBus) {
        MGEConfig.registerConfigs(modContainer);
        modEventBus.addListener(MGEConfig::onConfigLoad);
        addRegistrationListeners(modEventBus);
    }

    private void addRegistrationListeners(IEventBus modEventBus) {
        MGEBlocks.BLOCKS.register(modEventBus);
        MGEChemicals.CHEMICALS.register(modEventBus);
        MGEContainerTypes.CONTAINER_TYPES.register(modEventBus);
        MGEFluids.FLUIDS.register(modEventBus);
        MGECreativeTabs.CREATIVE_TABS.register(modEventBus);
        MGEItems.ITEMS.register(modEventBus);
        MGERecipeSerializerInternal.RECIPE_SERIALIZERS.register(modEventBus);
        MGERecipeType.RECIPE_TYPES.register(modEventBus);
        MGETileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    }

    public static ResourceLocation rl(String path){
        return ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, path);
    }
}
