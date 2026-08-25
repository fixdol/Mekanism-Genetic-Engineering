package com.fxd927.mekanismgeneticengineering.common.datagen.machines;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.datagen.builders.NuclearAcidCentrifugeRecipeBuilder;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.core.Holder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

@SuppressWarnings("removal")
public class NuclearAcidCentrifugeRecipeProvider {
    public static void buildRecipes(RecipeOutput recipeOutput) {
        String basePath = "nuclear_acid_centrifuge/";

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.ARMADILLO_SCUTE).getItemHolder()),
                new ChemicalStack(MGEChemicals.ARMADILLO_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "armadillo_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.BLAZE_ROD).getItemHolder()),
                new ChemicalStack(MGEChemicals.BLAZE_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "blaze_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.BREEZE_ROD).getItemHolder()),
                new ChemicalStack(MGEChemicals.BREEZE_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "breeze_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.CHICKEN).getItemHolder()),
                new ChemicalStack(MGEChemicals.CHICKEN_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "chicken_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.COD).getItemHolder()),
                new ChemicalStack(MGEChemicals.COD_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "cod_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.BEEF).getItemHolder()),
                new ChemicalStack(MGEChemicals.COW_DNA.get().getAsHolder(),500)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "cow_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.CREEPER_HEAD).getItemHolder()),
                new ChemicalStack(MGEChemicals.CREEPER_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "creeper_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.DRAGON_HEAD).getItemHolder()),
                new ChemicalStack(MGEChemicals.ENDER_DRAGON_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "ender_dragon_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.ENDER_PEARL).getItemHolder()),
                new ChemicalStack(MGEChemicals.ENDERMAN_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "enderman_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.FROGSPAWN).getItemHolder()),
                new ChemicalStack(MGEChemicals.FROG_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "frog_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.GHAST_TEAR).getItemHolder()),
                new ChemicalStack(MGEChemicals.GHAST_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "ghast_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.GLOW_INK_SAC).getItemHolder()),
                new ChemicalStack(MGEChemicals.GLOW_SQUID_DNA.get().getAsHolder(),500)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "glow_squid_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.GOAT_HORN).getItemHolder()),
                new ChemicalStack(MGEChemicals.GOAT_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "goat_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.MAGMA_CREAM).getItemHolder()),
                new ChemicalStack(MGEChemicals.MAGMA_CUBE_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "magma_cube_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.PHANTOM_MEMBRANE).getItemHolder()),
                new ChemicalStack(MGEChemicals.PHANTOM_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "phantom_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.PORKCHOP).getItemHolder()),
                new ChemicalStack(MGEChemicals.PIG_DNA.get().getAsHolder(),500)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "pig_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.PIGLIN_HEAD).getItemHolder()),
                new ChemicalStack(MGEChemicals.PIGLIN_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "piglin_dna"));
        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.PUFFERFISH).getItemHolder()),
                new ChemicalStack(MGEChemicals.PUFFERFISH_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "pufferfish_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.RABBIT).getItemHolder()),
                new ChemicalStack(MGEChemicals.RABBIT_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "rabbit_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.SALMON).getItemHolder()),
                new ChemicalStack(MGEChemicals.SALMON_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "salmon_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.MUTTON).getItemHolder()),
                new ChemicalStack(MGEChemicals.SHEEP_DNA.get().getAsHolder(),500)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "sheep_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.SHULKER_SHELL).getItemHolder()),
                new ChemicalStack(MGEChemicals.SHULKER_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "shulker_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.SLIME_BALL).getItemHolder()),
                new ChemicalStack(MGEChemicals.SLIME_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "slime_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.SNIFFER_EGG).getItemHolder()),
                new ChemicalStack(MGEChemicals.SNIFFER_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "sniffer_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.SPIDER_EYE).getItemHolder()),
                new ChemicalStack(MGEChemicals.SPIDER_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "spider_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.INK_SAC).getItemHolder()),
                new ChemicalStack(MGEChemicals.SQUID_DNA.get().getAsHolder(),500)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "squid_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.TROPICAL_FISH).getItemHolder()),
                new ChemicalStack(MGEChemicals.TROPICAL_FISH_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "tropical_fish_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.TURTLE_SCUTE).getItemHolder()),
                new ChemicalStack(MGEChemicals.TURTLE_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "turtle_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.NETHER_STAR).getItemHolder()),
                new ChemicalStack(MGEChemicals.WITHER_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "wither_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.WITHER_SKELETON_SKULL).getItemHolder()),
                new ChemicalStack(MGEChemicals.WITHER_SKELETON_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "wither_skeleton_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.ZOMBIE_HEAD).getItemHolder()),
                new ChemicalStack(MGEChemicals.ZOMBIE_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "zombie_dna"));

        NuclearAcidCentrifugeRecipeBuilder.nuclear_acid_centrifuge(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHENOL, 100),
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHLOROFORM, 100),
                IngredientCreatorAccess.item().fromHolder(new ItemStack(Items.SKELETON_SKULL).getItemHolder()),
                new ChemicalStack(MGEChemicals.SKELETON_DNA.get().getAsHolder(),1000)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "skeleton_dna"));




    }
}
