package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MGEChemicalConstants;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import mekanism.api.chemical.Chemical;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;

public class MGEChemicals {

    private MGEChemicals(){
    }

    public static final ChemicalDeferredRegister CHEMICALS = new ChemicalDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final DeferredChemical<Chemical> METHANE;
    public static final DeferredChemical<Chemical> CHLOROFORM;
    public static final DeferredChemical<Chemical> PHENOL;

    public static final DeferredChemical<Chemical> ARMADILLO_DNA;
    public static final DeferredChemical<Chemical> BLAZE_DNA;
    public static final DeferredChemical<Chemical> BREEZE_DNA;
    public static final DeferredChemical<Chemical> CAMEL_DNA;
    public static final DeferredChemical<Chemical> CHICKEN_DNA;
    public static final DeferredChemical<Chemical> COD_DNA;
    public static final DeferredChemical<Chemical> COW_DNA;
    public static final DeferredChemical<Chemical> CREEPER_DNA;
    public static final DeferredChemical<Chemical> ENDER_DRAGON_DNA;
    public static final DeferredChemical<Chemical> ENDERMAN_DNA;
    public static final DeferredChemical<Chemical> FOX_DNA;
    public static final DeferredChemical<Chemical> FROG_DNA;
    public static final DeferredChemical<Chemical> GHAST_DNA;
    public static final DeferredChemical<Chemical> GLOW_SQUID_DNA;
    public static final DeferredChemical<Chemical> GOAT_DNA;
    public static final DeferredChemical<Chemical> HORSE_DNA;
    public static final DeferredChemical<Chemical> MAGMA_CUBE_DNA;
    public static final DeferredChemical<Chemical> PARROT_DNA;
    public static final DeferredChemical<Chemical> PHANTOM_DNA;
    public static final DeferredChemical<Chemical> PIG_DNA;
    public static final DeferredChemical<Chemical> PIGLIN_DNA;
    public static final DeferredChemical<Chemical> PUFFERFISH_DNA;
    public static final DeferredChemical<Chemical> RABBIT_DNA;
    public static final DeferredChemical<Chemical> SALMON_DNA;
    public static final DeferredChemical<Chemical> SHEEP_DNA;
    public static final DeferredChemical<Chemical> SHULKER_DNA;
    public static final DeferredChemical<Chemical> SKELETON_DNA;
    public static final DeferredChemical<Chemical> SLIME_DNA;
    public static final DeferredChemical<Chemical> SNIFFER_DNA;
    public static final DeferredChemical<Chemical> SPIDER_DNA;
    public static final DeferredChemical<Chemical> SQUID_DNA;
    public static final DeferredChemical<Chemical> TROPICAL_FISH_DNA;
    public static final DeferredChemical<Chemical> TURTLE_DNA;
    public static final DeferredChemical<Chemical> WARDEN_DNA;
    public static final DeferredChemical<Chemical> WITHER_DNA;
    public static final DeferredChemical<Chemical> WITHER_SKELETON_DNA;
    public static final DeferredChemical<Chemical> WOLF_DNA;
    public static final DeferredChemical<Chemical> ZOMBIE_DNA;

    public static final DeferredChemical<Chemical> COLD_RESISTANCE_GENE;
    public static final DeferredChemical<Chemical> COLORFUL_GENE;
    public static final DeferredChemical<Chemical> FLIGHT_GENE;
    public static final DeferredChemical<Chemical> GENERAL_ENVIRONMENTAL_COMPATIBILITY_GENE;
    public static final DeferredChemical<Chemical> HEARING_GENE;
    public static final DeferredChemical<Chemical> HOT_RESISTANCE_GENE;
    public static final DeferredChemical<Chemical> INTELLIGENCE_GENE;
    public static final DeferredChemical<Chemical> JUMPING_GENE;
    public static final DeferredChemical<Chemical> PROLIFERATION_GENE;
    public static final DeferredChemical<Chemical> SMELL_GENE;
    public static final DeferredChemical<Chemical> SOFT_BODY_GENE;
    public static final DeferredChemical<Chemical> SPATIAL_AWARENESS_GENE;
    public static final DeferredChemical<Chemical> UNDEAD_GENE;
    public static final DeferredChemical<Chemical> UNDERWATER_ADAPTATION_GENE;
    public static final DeferredChemical<Chemical> USING_EFFECT_GENE;




    static {
        METHANE = CHEMICALS.register(MGEChemicalConstants.METHANE);
        CHLOROFORM = CHEMICALS.register(MGEChemicalConstants.CHLOROFORM);
        PHENOL = CHEMICALS.register(MGEChemicalConstants.PHENOL);

        ARMADILLO_DNA = CHEMICALS.register("armadillo_dna", 0xFEEF9C);
        BLAZE_DNA = CHEMICALS.register("blaze_dna", 0xFEEF9C);
        BREEZE_DNA = CHEMICALS.register("breeze_dna", 0xFEEF9C);
        CAMEL_DNA = CHEMICALS.register("camel_dna", 0xFEEF9C);
        CHICKEN_DNA = CHEMICALS.register("chicken_dna", 0xFEEF9C);
        COD_DNA = CHEMICALS.register("cod_dna", 0xFEEF9C);
        COW_DNA = CHEMICALS.register("cow_dna", 0xFEEF9C);
        CREEPER_DNA = CHEMICALS.register("creeper_dna", 0xFEEF9C);
        ENDER_DRAGON_DNA = CHEMICALS.register("ender_dragon_dna", 0xFEEF9C);
        ENDERMAN_DNA = CHEMICALS.register("enderman_dna", 0xFEEF9C);
        FOX_DNA = CHEMICALS.register("fox_dna", 0xFEEF9C);
        FROG_DNA = CHEMICALS.register("frog_dna", 0xFEEF9C);
        GHAST_DNA = CHEMICALS.register("ghast_dna", 0xFEEF9C);
        GLOW_SQUID_DNA = CHEMICALS.register("glow_squid_dna", 0xFEEF9C);
        GOAT_DNA = CHEMICALS.register("goat_dna", 0xFEEF9C);
        HORSE_DNA = CHEMICALS.register("horse_dna", 0xFEEF9C);
        MAGMA_CUBE_DNA = CHEMICALS.register("magma_cube_dna", 0xFEEF9C);
        PARROT_DNA = CHEMICALS.register("parrot_dna",0xFEEF9C);
        PHANTOM_DNA = CHEMICALS.register("phantom_dna", 0xFEEF9C);
        PIG_DNA = CHEMICALS.register("pig_dna", 0xFEEF9C);
        PIGLIN_DNA = CHEMICALS.register("piglin_dna", 0xFEEF9C);
        PUFFERFISH_DNA = CHEMICALS.register("pufferfish_dna", 0xFEEF9C);
        RABBIT_DNA = CHEMICALS.register("rabbit_dna", 0xFEEF9C);
        SALMON_DNA = CHEMICALS.register("salmon_dna", 0xFEEF9C);
        SHEEP_DNA = CHEMICALS.register("sheep_dna", 0xFEEF9C);
        SHULKER_DNA = CHEMICALS.register("shulker_dna", 0xFEEF9C);
        SKELETON_DNA = CHEMICALS.register("skeleton_dna", 0xFEEF9C);
        SLIME_DNA = CHEMICALS.register("slime_dna", 0xFEEF9C);
        SNIFFER_DNA = CHEMICALS.register("sniffer_dna", 0xFEEF9C);
        SPIDER_DNA = CHEMICALS.register("spider_dna", 0xFEEF9C);
        SQUID_DNA = CHEMICALS.register("squid_dna", 0xFEEF9C);
        TROPICAL_FISH_DNA = CHEMICALS.register("tropical_fish_dna", 0xFEEF9C);
        TURTLE_DNA = CHEMICALS.register("turtle_dna", 0xFEEF9C);
        WARDEN_DNA = CHEMICALS.register("warden_dna", 0xFEEF9C);
        WITHER_DNA = CHEMICALS.register("wither_dna", 0xFEEF9C);
        WITHER_SKELETON_DNA = CHEMICALS.register("wither_skeleton_dna", 0xFEEF9C);
        WOLF_DNA = CHEMICALS.register("wolf_dna", 0xFEEF9C);
        ZOMBIE_DNA = CHEMICALS.register("zombie_dna", 0xFEEF9C);

        COLD_RESISTANCE_GENE = CHEMICALS.register("cold_resistance_gene", 0xC0FAFF);
        COLORFUL_GENE = CHEMICALS.register("colorful_gene", 0xC0FAFF);
        FLIGHT_GENE = CHEMICALS.register("flight_gene", 0xC0FAFF);
        GENERAL_ENVIRONMENTAL_COMPATIBILITY_GENE = CHEMICALS.register("general_environmental_compatibility_gene", 0xC0FAFF);
        HEARING_GENE = CHEMICALS.register("hearing_gene", 0xC0FAFF);
        HOT_RESISTANCE_GENE = CHEMICALS.register("hot_resistance_gene", 0xFC6A6A);
        INTELLIGENCE_GENE = CHEMICALS.register("intelligence_gene", 0xC0FAFF);
        JUMPING_GENE = CHEMICALS.register("jumping_gene", 0xC0FAFF);
        PROLIFERATION_GENE = CHEMICALS.register("proliferation_gene", 0xC0FAFF);
        SMELL_GENE = CHEMICALS.register("smell_gene", 0xC0FAFF);
        SOFT_BODY_GENE = CHEMICALS.register("soft_body_gene", 0xC0FAFF);
        SPATIAL_AWARENESS_GENE = CHEMICALS.register("spatial_awareness_gene", 0xC0FAFF);
        UNDEAD_GENE = CHEMICALS.register("undead_gene", 0xFC6A6A);
        UNDERWATER_ADAPTATION_GENE = CHEMICALS.register("underwater_adaptation_gene", 0x406BFA);
        USING_EFFECT_GENE = CHEMICALS.register("using_effect_gene", 0xC0FAFF);
    }
}

