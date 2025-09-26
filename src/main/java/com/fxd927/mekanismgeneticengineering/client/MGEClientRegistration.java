package com.fxd927.mekanismgeneticengineering.client;

import com.fxd927.mekanismgeneticengineering.client.gui.machine.GuiGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.client.gui.machine.GuiNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.gui.machine.GuiChemicalInfuser;
import mekanism.common.registries.MekanismContainerTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        modid = "mekanismgeneticengineering",
        value = {Dist.CLIENT},
        bus = EventBusSubscriber.Bus.MOD
)
public class MGEClientRegistration {

    private MGEClientRegistration() {
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        ClientRegistrationUtil.registerScreen(event, MGEContainerTypes.NUCLEAR_ACID_CENTRIFUGE, GuiNuclearAcidCentrifuge::new);
        ClientRegistrationUtil.registerScreen(event, MGEContainerTypes.GENE_ANALYZER, GuiGeneAnalyzer::new);
    }
}
