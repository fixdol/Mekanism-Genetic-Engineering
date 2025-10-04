package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MGELang;
import com.fxd927.mekanismgeneticengineering.common.config.MGEConfig;
import com.fxd927.mekanismgeneticengineering.common.content.blocktype.MGEMachine;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityStructuralRestorationSynthesisMachine;
import mekanism.common.lib.transmitter.TransmissionType;

public class MGEBlockTypes {

    private MGEBlockTypes() {
    }

    public static final MGEMachine<TileEntityNuclearAcidCentrifuge> NUCLEAR_ACID_CENTRIFUGE = MGEMachine.MGEMachineBuilder
            .createMGEMachine(() -> MGETileEntityTypes.NUCLEAR_ACID_CENTRIFUGE, MGELang.DESCRIPTION_NUCLEAR_ACID_CENTRIFUGE)
            .withGui(() -> MGEContainerTypes.NUCLEAR_ACID_CENTRIFUGE)
            .withEnergyConfig(MGEConfig.mgeUsage.nuclearAcidCentrifuge, MGEConfig.mgeStorage.nuclearAcidCentrifuge)
            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ITEM, TransmissionType.ENERGY)
            .withComputerSupport("nuclearAcidCentrifuge")
            .build();

    public static final MGEMachine<TileEntityGeneAnalyzer> GENE_ANALYZER = MGEMachine.MGEMachineBuilder
            .createMGEMachine(() -> MGETileEntityTypes.GENE_ANALYZER, MGELang.DESCRIPTION_GENE_ANALYSIS)
            .withGui(() -> MGEContainerTypes.GENE_ANALYZER)
            .withEnergyConfig(MGEConfig.mgeUsage.geneAnalyzer, MGEConfig.mgeStorage.geneAnalyzer)
            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ITEM, TransmissionType.ENERGY)
            .withComputerSupport("geneAnalyzer")
            .build();

    public static final MGEMachine<TileEntityStructuralRestorationSynthesisMachine> STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE = MGEMachine.MGEMachineBuilder
            .createMGEMachine(() -> MGETileEntityTypes.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE, MGELang.DESCRIPTION_STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE)
            .withGui(() -> MGEContainerTypes.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE)
            .withEnergyConfig(MGEConfig.mgeUsage.structuralRestorationSynthesisMachine, MGEConfig.mgeStorage.structuralRestorationSynthesisMachine)
            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ITEM, TransmissionType.ENERGY)
            .withComputerSupport("structuralRestorationSynthesis")
            .build();
}
