package com.fxd927.mekanismgeneticengineering.common.datagen.machines;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.datagen.builders.DropsReproductionRecipeBuilder;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DropsReproductionRecipeProvider {
    public static void buildRecipes(RecipeOutput recipeOutput) {
        String basePath = "drops_reproduction/";

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.COD_DNA, 1000),
                new ItemStack(Items.COD),
                new ItemStack(Items.BONE_MEAL),
                0.1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "cod_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CHICKEN_DNA, 1000),
                new ItemStack(Items.CHICKEN),
                new ItemStack(Items.FEATHER),
                1,
                new ItemStack(Items.EGG),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "chicken_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.COW_DNA, 1000),
                new ItemStack(Items.BEEF, 2),
                new ItemStack(Items.LEATHER),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "cow_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.DOLPHIN_DNA, 1000),
                new ItemStack(Items.COD)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "dolphin_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.DONKEY_DNA, 1000),
                new ItemStack(Items.LEATHER)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "donkey_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.HORSE_DNA, 1000),
                new ItemStack(Items.LEATHER)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "horse_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.MULE_DNA, 1000),
                new ItemStack(Items.LEATHER)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "mule_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.RABBIT_DNA, 1000),
                new ItemStack(Items.RABBIT),
                new ItemStack(Items.RABBIT_HIDE),
                1,
                new ItemStack(Items.RABBIT_FOOT),
                0.2
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "rabbit_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SALMON_DNA, 1000),
                new ItemStack(Items.SALMON),
                new ItemStack(Items.BONE_MEAL),
                0.1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "salmon_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SQUID_DNA, 1000),
                new ItemStack(Items.INK_SAC,2)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "squid_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.GLOW_SQUID_DNA, 1000),
                new ItemStack(Items.GLOW_INK_SAC,2)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "glow_squid_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ARMADILLO_DNA, 1000),
                new ItemStack(Items.ARMADILLO_SCUTE)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "armadillo_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.BLAZE_DNA, 1000),
                new ItemStack(Items.BLAZE_ROD),
                new ItemStack(Items.BLAZE_POWDER),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "blaze_dna"));
        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.BREEZE_DNA, 1000),
                new ItemStack(Items.BREEZE_ROD),
                new ItemStack(Items.WIND_CHARGE),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "breeze_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ENDERMAN_DNA, 1000),
                new ItemStack(Items.ENDER_PEARL)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "enderman_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.GHAST_DNA, 1000),
                new ItemStack(Items.GHAST_TEAR),
                new ItemStack(Items.GUNPOWDER),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "ghast_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.MAGMA_CUBE_DNA, 1000),
                new ItemStack(Items.MAGMA_CREAM)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "magma_cube_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PHANTOM_DNA, 1000),
                new ItemStack(Items.PHANTOM_MEMBRANE)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "phantom_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PIG_DNA, 1000),
                new ItemStack(Items.PORKCHOP, 2)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "pig_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PUFFERFISH_DNA, 1000),
                new ItemStack(Items.PUFFERFISH),
                new ItemStack(Items.BONE_MEAL),
                0.1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "pufferfish_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SHEEP_DNA, 1000),
                new ItemStack(Items.MUTTON),
                new ItemStack(Items.WHITE_WOOL),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "sheep_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SLIME_DNA, 1000),
                new ItemStack(Items.SLIME_BALL)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "slime_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SHULKER_DNA, 1000),
                new ItemStack(Items.SHULKER_SHELL)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "shulker_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SPIDER_DNA, 1000),
                new ItemStack(Items.STRING, 1),
                new ItemStack(Items.SPIDER_EYE),
                0.5
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "spider_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.TROPICAL_FISH_DNA, 1000),
                new ItemStack(Items.TROPICAL_FISH),
                new ItemStack(Items.BONE_MEAL),
                0.1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "tropical_fish_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.WARDEN_DNA, 1000),
                new ItemStack(Items.SCULK_CATALYST)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "warden_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.WITHER_DNA, 1000),
                new ItemStack(Items.NETHER_STAR)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "wither_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.WITHER_SKELETON_DNA, 1000),
                new ItemStack(Items.BONE),
                new ItemStack(Items.COAL),
                1,
                new ItemStack(Items.WITHER_SKELETON_SKULL),
                0.03
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "wither_skeleton_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.MOOSHROOM_DNA, 1000),
                new ItemStack(Items.BEEF, 2),
                new ItemStack(Items.LEATHER),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "mooshroom_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CAT_DNA, 1000),
                new ItemStack(Items.STRING)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "cat_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.LLAMA_DNA, 1000),
                new ItemStack(Items.LEATHER)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "llama_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PANDA_DNA, 1000),
                new ItemStack(Items.BAMBOO)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "panda_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PARROT_DNA, 1000),
                new ItemStack(Items.FEATHER)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "parrot_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.POLAR_BEAR_DNA, 1000),
                new ItemStack(Items.COD),
                new ItemStack(Items.SALMON),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "polar_bear_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.STRIDER_DNA, 1000),
                new ItemStack(Items.STRING, 3)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "strider_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CAVE_SPIDER_DNA, 1000),
                new ItemStack(Items.STRING),
                new ItemStack(Items.COAL),
                0.3
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "cave_spider_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.CREEPER_DNA, 1000),
                new ItemStack(Items.GUNPOWDER)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "creeper_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.DROWNED_DNA, 1000),
                new ItemStack(Items.ROTTEN_FLESH),
                new ItemStack(Items.COPPER_INGOT),
                0.22
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "drowned_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ELDER_GUARDIAN_DNA, 1000),
                new ItemStack(Items.PRISMARINE_SHARD),
                new ItemStack(Items.PRISMARINE_CRYSTALS),
                1,
                new ItemStack(Items.WET_SPONGE),
                0.02
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "elder_guardian_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.EVOKER_DNA, 1000),
                new ItemStack(Items.EMERALD),
                new ItemStack(Items.TOTEM_OF_UNDYING),
                0.02
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "evoker_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.GUARDIAN_DNA, 200),
                new ItemStack(Items.PRISMARINE_SHARD),
                new ItemStack(Items.PRISMARINE_CRYSTALS),
                1,
                new ItemStack(Items.COD),
                0.5
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "guardian_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.HOGLIN_DNA, 200),
                new ItemStack(Items.PORKCHOP, 3),
                new ItemStack(Items.LEATHER),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "hoglin_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.PILLAGER_DNA, 1000),
                new ItemStack(Items.ARROW)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "pillager_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.RAVAGER_DNA, 1000),
                new ItemStack(Items.SADDLE)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "ravager_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.SKELETON_DNA, 1000),
                new ItemStack(Items.BONE),
                new ItemStack(Items.ARROW),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "skeleton_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.STRAY_DNA, 1000),
                new ItemStack(Items.BONE),
                new ItemStack(Items.ARROW),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "stray_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.VINDICATOR_DNA, 1000),
                new ItemStack(Items.EMERALD)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "vindicator_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.WITCH_DNA, 1000),
                new ItemStack(Items.GLASS_BOTTLE),
                new ItemStack(Items.GLOWSTONE_DUST),
                1,
                new ItemStack(Items.REDSTONE),
                1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "witch_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ZOGLIN_DNA, 1000),
                new ItemStack(Items.ROTTEN_FLESH, 2)
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "zoglin_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ZOMBIE_DNA, 1000),
                new ItemStack(Items.ROTTEN_FLESH),
                new ItemStack(Items.IRON_INGOT),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "zombie_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.HUSK_DNA, 1000),
                new ItemStack(Items.ROTTEN_FLESH),
                new ItemStack(Items.IRON_INGOT),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "husk_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ZOMBIE_VILLAGER_DNA, 1000),
                new ItemStack(Items.ROTTEN_FLESH),
                new ItemStack(Items.IRON_INGOT),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "zombie_villager_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.ZOMBIFIED_PIGLIN_DNA, 1000),
                new ItemStack(Items.ROTTEN_FLESH),
                new ItemStack(Items.GOLD_NUGGET),
                1,
                new ItemStack(Items.GOLD_INGOT),
                0.05
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "zombified_piglin_dna"));

        DropsReproductionRecipeBuilder.drops_reproduction(
                IngredientCreatorAccess.chemicalStack().fromHolder(MGEChemicals.BOGGED_DNA, 1000),
                new ItemStack(Items.BONE),
                new ItemStack(Items.ARROW),
               1
        ).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, basePath + "bogged_dna"));
       }
}
