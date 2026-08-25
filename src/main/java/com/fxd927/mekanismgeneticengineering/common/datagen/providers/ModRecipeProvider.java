package com.fxd927.mekanismgeneticengineering.common.datagen.providers;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.datagen.machines.ChemicalInfusingRecipeProvider;
import com.fxd927.mekanismgeneticengineering.common.datagen.machines.DropsReproductionRecipeProvider;
import com.fxd927.mekanismgeneticengineering.common.datagen.machines.NuclearAcidCentrifugeRecipeProvider;
import com.fxd927.mekanismgeneticengineering.common.datagen.machines.OxidizingRecipeProvider;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEItems;
import mekanism.common.registries.MekanismItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput writer) {

        RecipeOutput recipeOutput = new RecipeOutput() {
            @Override
            public void accept(ResourceLocation id, net.minecraft.world.item.crafting.Recipe<?> recipe,
                               net.minecraft.advancements.AdvancementHolder advancement) {
                writer.accept(id, recipe, null);
            }
            @Override
            public net.minecraft.advancements.Advancement.Builder advancement() {
                return writer.advancement();
            }
            @Override
            public void accept(ResourceLocation id, net.minecraft.world.item.crafting.Recipe<?> recipe,
                               net.minecraft.advancements.AdvancementHolder advancement, ICondition... conditions) {
                writer.accept(id, recipe, null, conditions);
            }
        };

        // Syringe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MGEItems.SYRINGE.get())
                .pattern(" I ")
                .pattern(" G ")
                .pattern(" G ")
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GLASS_PANE)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        //ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MSBlocks.RADIATION_IRRADIATOR.get().asItem())
          //      .pattern("CSC")
            //    .pattern("_X_")
              //  .pattern("CAC")
                //.define('A', MekanismItems.POLONIUM_PELLET.get())
                //.define('S', MekanismBlocks.LASER.get())
                //.define('C', MSItems.HIGH_QUALITY_CONCRETE_CLUMP.get())
                //.define('X', MekanismBlocks.STEEL_CASING.asItem())
                //.define('_', MekanismItems.ULTIMATE_CONTROL_CIRCUIT)
                //.unlockedBy("has_steel_casing", has(MekanismBlocks.STEEL_CASING.asItem()))
                //.save(recipeOutput);


        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(MekanismItems.COAL_DUST.get()),
                        RecipeCategory.MISC,
                        MGEItems.COAL_TAR.get(),
                        0.7f, 200)
                .group("coal_tar")
                .unlockedBy("has_dust_coal", has(MekanismItems.COAL_DUST.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, "smelting/coal_tar_from_dust"));

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(MekanismItems.COAL_DUST.get()),
                        RecipeCategory.MISC,
                        MGEItems.COAL_TAR.get(),
                        0.7f, 100)
                .group("coal_tar")
                .unlockedBy("has_dust_coal", has(MekanismItems.COAL_DUST.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, "blasting/coal_tar_from_dust"));


        ChemicalInfusingRecipeProvider.buildRecipes(recipeOutput);
        OxidizingRecipeProvider.buildRecipes(recipeOutput);
        DropsReproductionRecipeProvider.buildRecipes(recipeOutput);
        NuclearAcidCentrifugeRecipeProvider.buildRecipes(recipeOutput);
    }
}
