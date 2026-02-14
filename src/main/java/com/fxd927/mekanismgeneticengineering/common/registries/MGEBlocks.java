package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.attachments.chemical.MGEChemicalTanksBuilder;
import com.fxd927.mekanismgeneticengineering.common.attachments.components.MGEAttachedSideConfig;
import com.fxd927.mekanismgeneticengineering.common.attachments.item.MGEItemSlotsBuilder;
import com.fxd927.mekanismgeneticengineering.common.content.blocktype.MGEMachine;
import com.fxd927.mekanismgeneticengineering.common.recipe.MGERecipeType;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGEInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.recipe.lookup.cache.MGETripleInputRecipeCache;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityDropsReproducer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityGeneAnalyzer;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityNuclearAcidCentrifuge;
import com.fxd927.mekanismgeneticengineering.common.tile.machine.TileEntityStructuralRestorationSynthesisMachine;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismDataComponents;
import mekanism.common.resource.BlockResourceInfo;

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

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityStructuralRestorationSynthesisMachine, MGEMachine<TileEntityStructuralRestorationSynthesisMachine>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityStructuralRestorationSynthesisMachine, MGEMachine<TileEntityStructuralRestorationSynthesisMachine>>>> STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE =
            BLOCKS.register("structural_restoration_synthesis_machine", () -> new BlockTile.BlockTileModel<>(MGEBlockTypes.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())),
                    (block, properties) -> new ItemBlockTooltip<>(block, true, properties
                            .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                            .component(MekanismDataComponents.SIDE_CONFIG, MGEAttachedSideConfig.PROJECTING)
                    )
            ).forItemHolder(holder -> holder
                    .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> MGEChemicalTanksBuilder.builder()
                            .addBasic(TileEntityStructuralRestorationSynthesisMachine.MAX_CHEMICAL, MGERecipeType.STRUCTURAL_RESTORATION_SYNTHESIS, MGEInputRecipeCache.TriChemical::containsInputA)
                            .addBasic(TileEntityStructuralRestorationSynthesisMachine.MAX_CHEMICAL, MGERecipeType.STRUCTURAL_RESTORATION_SYNTHESIS, MGEInputRecipeCache.TriChemical::containsInputB)
                            .addBasic(TileEntityStructuralRestorationSynthesisMachine.MAX_CHEMICAL, MGERecipeType.STRUCTURAL_RESTORATION_SYNTHESIS, MGEInputRecipeCache.TriChemical::containsInputC)
                            .addBasic(TileEntityStructuralRestorationSynthesisMachine.MAX_CHEMICAL)
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

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityDropsReproducer, MGEMachine<TileEntityDropsReproducer>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityDropsReproducer, MGEMachine<TileEntityDropsReproducer>>>> DROPS_REPRODUCER =
            BLOCKS.register("drops_reproducer", () -> new BlockTile.BlockTileModel<>(MGEBlockTypes.DROPS_REPRODUCER, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())),
                    (block, properties) -> new ItemBlockTooltip<>(block, true, properties
                            .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                            .component(MekanismDataComponents.SIDE_CONFIG, MGEAttachedSideConfig.DROPS_REPRODUCTION)
                    )
            ).forItemHolder(holder -> holder
                    .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> MGEChemicalTanksBuilder.builder()
                            .addBasic(TileEntityDropsReproducer.MAX_CHEMICAL, MGERecipeType.DROPS_REPRODUCTION, MGEInputRecipeCache.SingleChemical::containsInput)
                            .addBasic(TileEntityDropsReproducer.MAX_CHEMICAL)
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
