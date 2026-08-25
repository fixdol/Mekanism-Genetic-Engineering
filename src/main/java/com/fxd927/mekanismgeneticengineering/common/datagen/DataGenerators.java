package com.fxd927.mekanismgeneticengineering.common.datagen;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.datagen.providers.LanguageGenerator;
import com.fxd927.mekanismgeneticengineering.common.datagen.providers.MGEBlockTagsProvider;
import com.fxd927.mekanismgeneticengineering.common.datagen.providers.ModLootTableProvider;
import com.fxd927.mekanismgeneticengineering.common.datagen.providers.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = MekanismGeneticEngineering.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(output, event.getLookupProvider()));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(output, event.getLookupProvider()));
        generator.addProvider(event.includeServer(), new MGEBlockTagsProvider(output, event.getLookupProvider(), existingFileHelper));
        //generator.addProvider(event.includeServer(), new AdvancementProvider(output, event.getLookupProvider(), existingFileHelper, List.of(new ModAdvancementProvider())));
        //generator.addProvider(event.includeServer(), new ModDataMapProvider(output, event.getLookupProvider()));

        // Language Generation
        generator.addProvider(event.includeClient(), new LanguageGenerator(output));
        //generator.addProvider(event.includeClient(), new LanguageGenerator.ESES(output));
        //generator.addProvider(event.includeClient(), new LanguageGenerator.ESES(output, "es_ar"));
        //generator.addProvider(event.includeClient(), new LanguageGenerator.ESES(output, "es_mx"));
        generator.addProvider(event.includeClient(), new LanguageGenerator.JAJP(output));
        //generator.addProvider(event.includeClient(), new LanguageGenerator.ZHCN(output));
    }
}
