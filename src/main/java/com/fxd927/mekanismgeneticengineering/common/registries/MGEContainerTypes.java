package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityTraitDecodingProjector;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tile.machine.TileEntityChemicalInfuser;

public class MGEContainerTypes {

    private MGEContainerTypes(){
    }

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityNuclearAcidCentrifuge>> NUCLEAR_ACID_CENTRIFUGE = CONTAINER_TYPES.custom(MGEBlocks.NUCLEAR_ACID_CENTRIFUGE, TileEntityNuclearAcidCentrifuge.class).offset(0, 5).build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityGeneAnalyzer>> GENE_ANALYZER = CONTAINER_TYPES.register(MGEBlocks.GENE_ANALYZER, TileEntityGeneAnalyzer.class);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityTraitDecodingProjector>> TRAIT_DECODING_PROJECTOR = CONTAINER_TYPES.register(MGEBlocks.TRAIT_DECODING_PROJECTOR, TileEntityTraitDecodingProjector.class);

}
