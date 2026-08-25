package com.fxd927.mekanismgeneticengineering.common.datagen.providers;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MGEBlockTagsProvider extends BlockTagsProvider {
    public MGEBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MekanismGeneticEngineering.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MGEBlocks.DROPS_REPRODUCER.get())
                .add(MGEBlocks.NUCLEAR_ACID_CENTRIFUGE.get())
                .add(MGEBlocks.GENE_ANALYZER.get())
                .add(MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(MGEBlocks.DROPS_REPRODUCER.get())
                .add(MGEBlocks.NUCLEAR_ACID_CENTRIFUGE.get())
                .add(MGEBlocks.GENE_ANALYZER.get())
                .add(MGEBlocks.STRUCTURAL_RESTORATION_SYNTHESIS_MACHINE.get());
    }
}
