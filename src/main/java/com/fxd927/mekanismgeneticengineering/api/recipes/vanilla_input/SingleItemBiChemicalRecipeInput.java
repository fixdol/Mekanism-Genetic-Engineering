package com.fxd927.mekanismgeneticengineering.api.recipes.vanilla_input;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.vanilla_input.ItemChemicalRecipeInput;
import mekanism.api.recipes.vanilla_input.SingleItemChemicalRecipeInput;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public record SingleItemBiChemicalRecipeInput(ItemStack item, ChemicalStack firstChemical, ChemicalStack secondChemical) implements ItemChemicalRecipeInput {
    @Override
    public ItemStack getItem(int index) {
        if (index != 0) {
            throw new IllegalArgumentException("No item for index " + index);
        }
        return item;
    }

    @Override
    public ChemicalStack getChemical(int index) {
        if (index == 0) {
            return firstChemical;
        } else if (index == 1) {
            return secondChemical;
        }
        throw new IllegalArgumentException("No chemical for index " + index);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return item.isEmpty() || firstChemical.isEmpty() || secondChemical.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SingleItemBiChemicalRecipeInput other = (SingleItemBiChemicalRecipeInput) o;
        return firstChemical.equals(other.firstChemical) && ItemStack.matches(item, other.item);
    }

    @Override
    public int hashCode() {
        int hash = firstChemical.hashCode();
        hash = 31 * hash + ItemStack.hashItemAndComponents(item);
        hash = 31 + hash + item.getCount();
        return hash;
    }
}
