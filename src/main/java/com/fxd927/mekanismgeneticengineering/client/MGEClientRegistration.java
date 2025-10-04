package com.fxd927.mekanismgeneticengineering.client;

import com.fxd927.mekanismgeneticengineering.client.gui.machine.GuiGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.client.gui.machine.GuiNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.client.gui.machine.GuiStructuralRestorationSynthesisMachine;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        modid = "mekanismgeneticengineering",
        value = {Dist.CLIENT}
)
public class MGEClientRegistration {

    private MGEClientRegistration() {
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        ClientRegistrationUtil.registerScreen(event, MGEContainerTypes.NUCLEAR_ACID_CENTRIFUGE, GuiNuclearAcidCentrifuge::new);
        ClientRegistrationUtil.registerScreen(event, MGEContainerTypes.GENE_ANALYZER, GuiGeneAnalyzer::new);
        ClientRegistrationUtil.registerScreen(event, MGEContainerTypes.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE, GuiStructuralRestorationSynthesisMachine::new);
    }
}
