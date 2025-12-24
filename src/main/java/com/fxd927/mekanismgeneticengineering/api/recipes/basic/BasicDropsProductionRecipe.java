package com.fxd927.mekanismgeneticengineering.api.recipes.basic;

import com.fxd927.mekanismgeneticengineering.api.recipes.DropsReproductionRecipe;
import com.fxd927.mekanismgeneticengineering.api.recipes.MGERecipeSerializers;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Contract;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NothingNullByDefault
public class BasicDropsProductionRecipe extends DropsReproductionRecipe {
    protected final ChemicalStackIngredient input;
    protected final ItemStack firstOutput;
    protected final ItemStack secondOutput;
    protected final ItemStack thirdOutput;
    protected final double secondChance;
    protected final double thirdChance;


    public BasicDropsProductionRecipe(ChemicalStackIngredient input, ItemStack firstOutput, ItemStack secondOutput, ItemStack thirdOutput, double secondChance, double thirdChance) {
        this.input = Objects.requireNonNull(input, "Input cannot be null.");
        Objects.requireNonNull(firstOutput, "Main output cannot be null.");
        Objects.requireNonNull(secondOutput, "Secondary output cannot be null.");
        if (firstOutput.isEmpty() && secondOutput.isEmpty() && thirdOutput.isEmpty()) {
            throw new IllegalArgumentException("At least one output must not be empty.");
        } else if (secondChance < 0 || secondChance > 1) {
            throw new IllegalArgumentException("Secondary output chance must be at least zero and at most one.");
        }else if (thirdChance < 0 || thirdChance > 1) {
            throw new IllegalArgumentException("Secondary output chance must be at least zero and at most one.");
        } else if (firstOutput.isEmpty()) {
            if (secondChance == 0 || secondChance == 1) {
                throw new IllegalArgumentException("Secondary output must have a chance greater than zero and less than one.");
            }
            if (thirdChance == 0 || thirdChance == 1) {
                throw new IllegalArgumentException("Secondary output must have a chance greater than zero and less than one.");
            }
        } else if (secondOutput.isEmpty() && secondChance != 0) {
            throw new IllegalArgumentException("If there is no secondary output, the chance of getting the secondary output should be zero.");
        }
        else if (thirdOutput.isEmpty() && thirdChance != 0) {
            throw new IllegalArgumentException("If there is no secondary output, the chance of getting the secondary output should be zero.");
        }
        this.firstOutput = firstOutput.copy();
        this.secondOutput = secondOutput.copy();
        this.secondChance = secondChance;
        this.thirdOutput = thirdOutput.copy();
        this.thirdChance = thirdChance;
    }

    @Override
    public boolean test(ChemicalStack stack) {
        return this.input.test(stack);
    }

    @Override
    @Contract("_ -> new")
    public ChanceOutput getOutput(ChemicalStack input) {
        return new BasicChanceOutput(
                secondChance > 0 ? RANDOM.nextDouble() : 0,
                thirdChance > 0 ? RANDOM.nextDouble() : 0
        );
    }


    @Override
    public List<ItemStack> getFirstOutputDefinition() {
        return firstOutput.isEmpty() ? Collections.emptyList() : Collections.singletonList(firstOutput);
    }

    @Override
    public List<ItemStack> getSecondOutputDefinition() {
        return secondOutput.isEmpty() ? Collections.emptyList() : Collections.singletonList(secondOutput);
    }

    @Override
    public List<ItemStack> getThirdOutputDefinition() {
        return thirdOutput.isEmpty() ? Collections.emptyList() : Collections.singletonList(thirdOutput);
    }

    @Override
    public double getSecondChance() {
        return secondChance;
    }

    @Override
    public double getThirdChance() {
        return thirdChance;
    }

    @Override
    public ChemicalStackIngredient getInput() {
        return input;
    }

    /**
     * For Serializer use. DO NOT MODIFY RETURN VALUE.
     *
     * @return the uncopied basic output, or empty if the value is ItemStack.EMPTY
     */
    public Optional<ItemStack> getFirstOutputRaw() {
        return this.firstOutput.isEmpty() ? Optional.empty() : Optional.of(this.firstOutput);
    }

    /**
     * For Serializer use. DO NOT MODIFY RETURN VALUE.
     *
     * @return the uncopied basic output
     */
    public Optional<ItemStack> getSecondaryOutputRaw() {
        return this.secondOutput.isEmpty() ? Optional.empty() : Optional.of(this.secondOutput);
    }

    public Optional<ItemStack> getThirdOutputRaw() {
        return this.thirdOutput.isEmpty() ? Optional.empty() : Optional.of(this.thirdOutput);
    }

    @Override
    public RecipeSerializer<BasicDropsProductionRecipe> getSerializer() {
        return MGERecipeSerializers.DROPS_REPRODUCTION.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicDropsProductionRecipe other = (BasicDropsProductionRecipe) o;
        return Double.compare(secondChance, other.secondChance) == 0
                && Double.compare(thirdChance, other.thirdChance) == 0
                && input.equals(other.input)
                && ItemStack.matches(firstOutput, other.firstOutput)
                && ItemStack.matches(secondOutput, other.secondOutput)
                && ItemStack.matches(thirdOutput, other.thirdOutput);
    }

    @Override
    public int hashCode() {
        int hash = 31 * input.hashCode() + Double.hashCode(secondChance);
        hash = 31 * hash + Double.hashCode(thirdChance);
        hash = 31 * hash + ItemStack.hashItemAndComponents(firstOutput);
        hash = 31 * hash + firstOutput.getCount();
        if (!secondOutput.isEmpty()) {
            hash = 31 * hash + ItemStack.hashItemAndComponents(secondOutput);
            hash = 31 * hash + secondOutput.getCount();
        }
        if (!thirdOutput.isEmpty()) {
            hash = 31 * hash + ItemStack.hashItemAndComponents(thirdOutput);
            hash = 31 * hash + thirdOutput.getCount();
        }
        return hash;
    }

    public class BasicChanceOutput implements ChanceOutput {

        protected final double randSecond;
        protected final double randThird;

        protected BasicChanceOutput(double randSecond, double randThird) {
            this.randSecond = randSecond;
            this.randThird = randThird;
        }

        @Override
        public ItemStack getFirstOutput() {
            return firstOutput.copy();
        }

        @Override
        public ItemStack getMaxSecondOutput() {
            return secondOutput.isEmpty() ? ItemStack.EMPTY : secondOutput.copy();
        }

        @Override
        public ItemStack getMaxThirdOutput() {
            return thirdOutput.isEmpty() ? ItemStack.EMPTY : thirdOutput.copy();
        }

        @Override
        public ItemStack getSecondOutput() {
            if (!secondOutput.isEmpty() && randSecond <= secondChance) {
                return secondOutput.copy();
            }
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack getThirdOutput() {
            if (!thirdOutput.isEmpty() && randThird <= thirdChance) {
                return thirdOutput.copy();
            }
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack nextSecondOutput() {
            if (secondChance > 0 && !secondOutput.isEmpty()) {
                double rand = RANDOM.nextDouble();
                if (rand <= secondChance) {
                    return secondOutput.copy();
                }
            }
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack nextThirdOutput() {
            if (thirdChance > 0 && !thirdOutput.isEmpty()) {
                double rand = RANDOM.nextDouble();
                if (rand <= thirdChance) {
                    return thirdOutput.copy();
                }
            }
            return ItemStack.EMPTY;
        }
    }
}
