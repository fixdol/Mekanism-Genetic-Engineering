package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MGELang;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registries.MekanismCreativeTabs;
import net.minecraft.world.item.CreativeModeTab;

public class MGECreativeTabs {

    public static final CreativeTabDeferredRegister CREATIVE_TABS = new CreativeTabDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> MEKANISM_GENETIC_ENGINEERING = CREATIVE_TABS.registerMain(MGELang.MEKANISM_GENETIC_ENGINEERING,
            MGEItems.SYRINGE, builder ->
                    builder.withTabsBefore(MekanismCreativeTabs.MEKANISM.getId())
                            .displayItems((displayParameters, output) -> {
                                CreativeTabDeferredRegister.addToDisplay(MGEItems.ITEMS, output);
                                CreativeTabDeferredRegister.addToDisplay(MGEBlocks.BLOCKS, output);
                                CreativeTabDeferredRegister.addToDisplay(MGEFluids.FLUIDS, output);
                            })
    );
}
