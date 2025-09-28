package com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.vanilla_input.ChemicalRecipeInput;

@NothingNullByDefault
public record TriChemicalRecipeInput(ChemicalStack firstChemical, ChemicalStack secondChemical, ChemicalStack thirdChemical) implements ChemicalRecipeInput {
    @Override
    public ChemicalStack getChemical(int index) {
        return switch (index) {
            case 0 -> firstChemical;
            case 1 -> secondChemical;
            case 2 -> thirdChemical;
            default -> throw new IllegalArgumentException("No chemical for index " + index);
        };
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return firstChemical.isEmpty() || secondChemical.isEmpty();
    }
}
