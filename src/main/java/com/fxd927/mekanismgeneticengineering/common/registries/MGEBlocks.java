package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input.SingleItemBiChemicalRecipeInput;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.attachments.chemical.MGEChemicalTanksBuilder;
import com.fxd927.mekanismgeneticengineering.common.attachments.components.MGEAttachedSideConfig;
import com.fxd927.mekanismgeneticengineering.common.attachments.item.MGEItemSlotsBuilder;
import com.fxd927.mekanismgeneticengineering.common.content.blocktype.MGEMachine;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGETripleInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityTraitDecodingProjector;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.recipe.lookup.cache.SingleInputRecipeCache;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registries.MekanismBlockTypes;
import mekanism.common.registries.MekanismDataComponents;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.machine.TileEntityChemicalInfuser;

public class MGEBlocks {

    private MGEBlocks(){
    }

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismGeneticEngineering.MODID);


    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityNuclearAcidCentrifuge, MGEMachine<TileEntityNuclearAcidCentrifuge>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityNuclearAcidCentrifuge, MGEMachine<TileEntityNuclearAcidCentrifuge>>>> NUCLEAR_ACID_CENTRIFUGE =
            BLOCKS.register("nuclear_acid_centrifuge", () -> new BlockTile.BlockTileModel<>(MGEBlockTypes.NUCLEAR_ACID_CENTRIFUGE, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())),
                    (block, properties) -> new ItemBlockTooltip<>(block, true, properties
                            .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                            .component(MekanismDataComponents.SIDE_CONFIG, MGEAttachedSideConfig.NUCLEAR_ACID_CENTRIFUGE)
                    )
            ).forItemHolder(holder -> holder
                    .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> MGEChemicalTanksBuilder.builder()
                            .addBasic(TileEntityNuclearAcidCentrifuge.MAX_CHEMICAL, MGERecipeType.NUCLEAR_ACID_CENTRIFUGE, MGEInputRecipeCache.ItemChemicalChemical::containsInputB)
                            .addBasic(TileEntityNuclearAcidCentrifuge.MAX_CHEMICAL, MGERecipeType.NUCLEAR_ACID_CENTRIFUGE, MGEInputRecipeCache.ItemChemicalChemical::containsInputC)
                            .addBasic(TileEntityNuclearAcidCentrifuge.MAX_CHEMICAL)
                            .build()
                    ).addAttachmentOnlyContainers(ContainerType.ITEM, () -> MGEItemSlotsBuilder.builder()
                            .addInput(MGERecipeType.NUCLEAR_ACID_CENTRIFUGE, MGETripleInputRecipeCache::containsInputA)
                            .addChemicalFillSlot(0)
                            .addChemicalFillSlot(1)
                            .addChemicalDrainSlot(2)
                            .addEnergy()
                            .build()
                    )
            );

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityGeneAnalyzer, MGEMachine<TileEntityGeneAnalyzer>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityGeneAnalyzer, MGEMachine<TileEntityGeneAnalyzer>>>> GENE_ANALYZER =
            BLOCKS.register("gene_analyzer", () -> new BlockTile.BlockTileModel<>(MGEBlockTypes.GENE_ANALYZER, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())),
                    (block, properties) -> new ItemBlockTooltip<>(block, true, properties
                            .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                            .component(MekanismDataComponents.SIDE_CONFIG, MGEAttachedSideConfig.GENE_ANALYZER)
                    )
            ).forItemHolder(holder -> holder
                    .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> MGEChemicalTanksBuilder.builder()
                            .addBasic(TileEntityGeneAnalyzer.MAX_CHEMICAL, MGERecipeType.GENE_ANALYSIS, MGEInputRecipeCache.SingleChemical::containsInput)
                            .addBasic(TileEntityGeneAnalyzer.MAX_CHEMICAL)
                            .addBasic(TileEntityGeneAnalyzer.MAX_CHEMICAL)
                            .addBasic(TileEntityGeneAnalyzer.MAX_CHEMICAL)
                            .build()
                    ).addAttachmentOnlyContainers(ContainerType.ITEM, () -> MGEItemSlotsBuilder.builder()
                            .addChemicalFillSlot(0)
                            .addChemicalFillSlot(1)
                            .addChemicalFillSlot(2)
                            .addChemicalDrainSlot(3)
                            .addEnergy()
                            .build()
                    )
            );

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityTraitDecodingProjector, MGEMachine<TileEntityTraitDecodingProjector>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityTraitDecodingProjector, MGEMachine<TileEntityTraitDecodingProjector>>>> TRAIT_DECODING_PROJECTOR =
            BLOCKS.register("trait_decoding_projector", () -> new BlockTile.BlockTileModel<>(MGEBlockTypes.TRAIT_DECODING_PROJECTOR, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())),
                    (block, properties) -> new ItemBlockTooltip<>(block, true, properties
                            .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                            .component(MekanismDataComponents.SIDE_CONFIG, MGEAttachedSideConfig.PROJECTING)
                    )
            ).forItemHolder(holder -> holder
                    .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> MGEChemicalTanksBuilder.builder()
                            .addBasic(TileEntityTraitDecodingProjector.MAX_CHEMICAL, MGERecipeType.PROJECTING, MGEInputRecipeCache.EitherSideChemical::containsInput)
                            .addBasic(TileEntityTraitDecodingProjector.MAX_CHEMICAL)
                            .addBasic(TileEntityTraitDecodingProjector.MAX_CHEMICAL)
                            .addBasic(TileEntityTraitDecodingProjector.MAX_CHEMICAL)
                            .build()
                    ).addAttachmentOnlyContainers(ContainerType.ITEM, () -> MGEItemSlotsBuilder.builder()
                            .addChemicalFillSlot(0)
                            .addChemicalFillSlot(1)
                            .addChemicalFillSlot(2)
                            .addChemicalDrainSlot(3)
                            .addEnergy()
                            .build()
                    )
            );
}
