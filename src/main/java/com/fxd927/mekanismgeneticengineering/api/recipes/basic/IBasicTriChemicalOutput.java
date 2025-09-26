package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import mekanism.api.chemical.ChemicalStack;

public interface IBasicTriChemicalOutput {
    ChemicalStack getFirstOutputRaw();

    ChemicalStack getSecondOutputRaw();

    ChemicalStack getThirdOutputRaw();

}
