package com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.vanilla_input.ChemicalRecipeInput;
import mekanism.api.recipes.vanilla_input.ItemChemicalRecipeInput;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public record TriChemicalRecipeInput(ChemicalStack firstChemical, ChemicalStack secondChemical, ChemicalStack thirdChemical) implements ChemicalRecipeInput {
    @Override
    public ChemicalStack getChemical(int index) {
        if (index == 0) {
            return firstChemical;
        } else if (index == 1) {
            return secondChemical;
        } else if (index == 2) {
            return thirdChemical;
        }
        throw new IllegalArgumentException("No chemical for index " + index);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return firstChemical.isEmpty() || secondChemical.isEmpty() || thirdChemical.isEmpty();
    }
}
