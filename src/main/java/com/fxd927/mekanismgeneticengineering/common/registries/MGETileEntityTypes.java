package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityDropsReproducer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityStructuralRestorationSynthesisMachine;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

public class MGETileEntityTypes {

    private MGETileEntityTypes(){
    }

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final TileEntityTypeRegistryObject<TileEntityNuclearAcidCentrifuge> NUCLEAR_ACID_CENTRIFUGE = TILE_ENTITY_TYPES
            .mekBuilder(MGEBlocks.NUCLEAR_ACID_CENTRIFUGE, TileEntityNuclearAcidCentrifuge::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityGeneAnalyzer> GENE_ANALYZER = TILE_ENTITY_TYPES
            .mekBuilder(MGEBlocks.GENE_ANALYZER, TileEntityGeneAnalyzer::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityStructuralRestorationSynthesisMachine> STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE = TILE_ENTITY_TYPES
            .mekBuilder(MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE, TileEntityStructuralRestorationSynthesisMachine::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityDropsReproducer> DROPS_REPRODUCER = TILE_ENTITY_TYPES
            .mekBuilder(MGEBlocks.DROPS_REPRODUCER, TileEntityDropsReproducer::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();

}
