package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityTraitDecodingProjector;
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
    public static final TileEntityTypeRegistryObject<TileEntityTraitDecodingProjector> TRAIT_DECODING_PROJECTOR = TILE_ENTITY_TYPES
            .mekBuilder(MGEBlocks.TRAIT_DECODING_PROJECTOR, TileEntityTraitDecodingProjector::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();

}
