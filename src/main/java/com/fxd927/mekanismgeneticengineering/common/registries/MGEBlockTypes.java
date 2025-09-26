package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MGELang;
import com.fxd927.mekanismgeneticengineering.common.config.MGEConfig;
import com.fxd927.mekanismgeneticengineering.common.content.blocktype.MGEMachine;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
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
            .createMGEMachine(() -> MGETileEntityTypes.GENE_ANALYZER, MGELang.DESCRIPTION_NUCLEAR_ACID_CENTRIFUGE)
            .withGui(() -> MGEContainerTypes.GENE_ANALYZER)
            .withEnergyConfig(MGEConfig.mgeUsage.nuclearAcidCentrifuge, MGEConfig.mgeStorage.nuclearAcidCentrifuge)
            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ITEM, TransmissionType.ENERGY)
            .withComputerSupport("geneAnalyzer")
            .build();
}
