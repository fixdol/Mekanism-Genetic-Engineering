package com.fxd927.mekanismgeneticengineering.api.recipes;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.vanilla_input.SingleChemicalRecipeInput;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

@NothingNullByDefault
public abstract class DropsReproductionRecipe extends MekanismRecipe<SingleChemicalRecipeInput> implements Predicate<@NotNull ChemicalStack> {
    protected static final RandomSource RANDOM = RandomSource.create();
    private static final Holder<Item> DROPS_REPRODUCER = DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, "drops_reproducer"));

    @Override
    public abstract boolean test(ChemicalStack stack);

    @Override
    public boolean matches(SingleChemicalRecipeInput input, Level level) {
        return !isIncomplete() && test(input.chemical());
    }

    @Contract("_ -> new")
    public abstract DropsReproductionRecipe.ChanceOutput getOutput(ChemicalStack input);

    /**
     * For JEI, gets the main output representations to display.
     *
     * @return Representation of the main output, <strong>MUST NOT</strong> be modified.
     */
    public abstract List<ItemStack> getFirstOutputDefinition();

    /**
     * For JEI, gets the secondary output representations to display.
     *
     * @return Representation of the secondary output, <strong>MUST NOT</strong> be modified.
     */
    public abstract List<ItemStack> getSecondOutputDefinition();

    public abstract List<ItemStack> getThirdOutputDefinition();


    /**
     * Gets the chance (between 0 and 1) of the secondary output being produced.
     */
    public abstract double getSecondChance();

    public abstract double getThirdChance();


    /**
     * Gets the input ingredient.
     */
    public abstract ChemicalStackIngredient getInput();

    @Override
    public boolean isIncomplete() {
        return getInput().hasNoMatchingInstances();
    }

    @Override
    public void logMissingTags() {
        getInput().logMissingTags();
    }

    @Override
    public final RecipeType<DropsReproductionRecipe> getType() {
        return MGERecipeTypes.TYPE_DROPS_REPRODUCTION.value();
    }

    @Override
    public String getGroup() {
        return "drops_reproducer";
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(DROPS_REPRODUCER);
    }

    /**
     * Represents a precalculated chance based output. This output keeps track of what random value was calculated for use in comparing if the secondary output should be
     * created.
     */
    public interface ChanceOutput {

        /**
         * Gets a copy of the main output of this recipe. This may be empty if there is only a secondary chance based output.
         *
         * @implNote return a new copy
         */
        ItemStack getFirstOutput();

        /**
         * Gets a copy of the secondary output ignoring the random chance of it happening. This is mostly used for checking the maximum amount we can get as a secondary
         * output for purposes of seeing if we have space to process.
         *
         * @implNote return a new copy or ItemStack.EMPTY
         */
        ItemStack getMaxSecondOutput();

        /**
         * Gets a copy of the secondary output if the random number generated for this output matches the chance of a secondary output being produced, otherwise returns
         * an empty stack.
         *
         * @implNote return a new copy or ItemStack.EMPTY
         */

        ItemStack getSecondOutput();


        ItemStack getMaxThirdOutput();

        /**
         * Gets a copy of the secondary output if the random number generated for this output matches the chance of a secondary output being produced, otherwise returns
         * an empty stack.
         *
         * @implNote return a new copy or ItemStack.EMPTY
         */

        ItemStack getThirdOutput();


        ItemStack nextSecondOutput();

        ItemStack nextThirdOutput();

    }
}
