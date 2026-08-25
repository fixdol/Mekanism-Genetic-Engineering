package com.fxd927.mekanismgeneticengineering.common.datagen.providers;

import com.fxd927.mekanismgeneticengineering.common.registries.MGEBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

public class MGEBlockLootTables extends BlockLootSubProvider {
    protected MGEBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(MGEBlocks.DROPS_REPRODUCER.get());
        dropSelf(MGEBlocks.NUCLEAR_ACID_CENTRIFUGE.get());
        dropSelf(MGEBlocks.GENE_ANALYZER.get());
        dropSelf(MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE.get());
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(
                MGEBlocks.DROPS_REPRODUCER.get(),
                MGEBlocks.NUCLEAR_ACID_CENTRIFUGE.get(),
                MGEBlocks.GENE_ANALYZER.get(),
                MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE.get());
    }
}
