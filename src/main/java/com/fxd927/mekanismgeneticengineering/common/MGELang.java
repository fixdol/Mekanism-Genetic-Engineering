package com.fxd927.mekanismgeneticengineering.common;

import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

public enum MGELang implements ILangEntry {

    MEKANISM_GENETIC_ENGINEERING("constants","mod_name"),
    DESCRIPTION_NUCLEAR_ACID_CENTRIFUGE("description","nuclear_acid_centrifuge"),
    DESCRIPTION_GENE_ANALYSIS("description","gene_analysis"),
    DESCRIPTION_TRAIT_DECODING_PROJECTOR("description","trait_decoding_projector");


    private final String key;

    MGELang(String type,String path){
        this(Util.makeDescriptionId(type, MekanismGeneticEngineering.rl(path)));
    }

    MGELang(String key){
        this.key = key;
    }

    @Override
    public String getTranslationKey(){
        return key;
    }
}
