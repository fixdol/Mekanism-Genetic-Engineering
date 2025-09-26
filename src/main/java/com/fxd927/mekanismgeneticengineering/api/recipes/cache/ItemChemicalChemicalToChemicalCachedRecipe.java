package com.fxd927.mekanismgeneticengineering.api.recipes.cache;

import com.fxd927.mekanismgeneticengineering.api.recipes.ItemChemicalChemicalToChemicalRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.*;

@NothingNullByDefault
public class ItemChemicalChemicalToChemicalCachedRecipe extends CachedRecipe<ItemChemicalChemicalToChemicalRecipe> {
    private final IOutputHandler<ChemicalStack> outputHandler;
    private final IInputHandler<ItemStack> itemInputHandler;
    private final IInputHandler<ChemicalStack> gasFirstInputHandler;
    private final IInputHandler<ChemicalStack> gasSecondInputHandler;

    private ItemStack recipeItem = ItemStack.EMPTY;
    private ChemicalStack firstGasRecipeInput = ChemicalStack.EMPTY;
    private ChemicalStack secondGasRecipeInput = ChemicalStack.EMPTY;

    @Nullable
    private ChemicalStack output;

    /**
     * @param recipe            Recipe.
     * @param recheckAllErrors  Returns {@code true} if processing should be continued even if an error is hit in order to gather all the errors. It is recommended to not
     *                          do this every tick or if there is no one viewing recipes.
     * @param gasFirstInputHandler  Left input handler.
     * @param gasSecondInputHandler Right input handler.
     * @param outputHandler     Output handler.
     */
    public ItemChemicalChemicalToChemicalCachedRecipe(ItemChemicalChemicalToChemicalRecipe recipe, BooleanSupplier recheckAllErrors, IInputHandler<@NotNull ItemStack> itemInputHandler,
                                           IInputHandler<@NotNull ChemicalStack> gasFirstInputHandler, IInputHandler<@NotNull ChemicalStack> gasSecondInputHandler,
                                           IOutputHandler<ChemicalStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.itemInputHandler = Objects.requireNonNull(itemInputHandler, "Item input handler cannot be null.");
        this.gasFirstInputHandler = Objects.requireNonNull(gasFirstInputHandler, "Fluid input handler cannot be null.");
        this.gasSecondInputHandler = Objects.requireNonNull(gasSecondInputHandler, "Chemical input handler cannot be null.");
        this.outputHandler = Objects.requireNonNull(outputHandler, "Output handler cannot be null.");
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (tracker.shouldContinueChecking()) {
            //TODO - 1.18: If they are empty that means that we either don't have any or don't have enough of the input (or it doesn't match)
            // how do we want to best check if we have some but just not enough for the recipe?
            // Also figure this out for our other recipes that have multiple inputs
            recipeItem = itemInputHandler.getRecipeInput(recipe.getItemInput());
            //Test to make sure we can even perform a single operation. This is akin to !recipe.test(inputItem)
            if (recipeItem.isEmpty()) {
                //No input, we don't know if the recipe matches or not so treat it as not matching
                tracker.mismatchedRecipe();
            } else {
                firstGasRecipeInput = gasFirstInputHandler.getRecipeInput(recipe.getFirstInput());
                //Test to make sure we can even perform a single operation. This is akin to !recipe.test(inputFluid)
                if (firstGasRecipeInput.isEmpty()) {
                    //No input, we don't know if the recipe matches or not so treat it as not matching
                    tracker.mismatchedRecipe();
                } else {
                    secondGasRecipeInput = gasSecondInputHandler.getRecipeInput(recipe.getSecondInput());
                    //Test to make sure we can even perform a single operation. This is akin to !recipe.test(inputChemical)
                    if (secondGasRecipeInput.isEmpty()) {
                        //No input, we don't know if the recipe matches or not so treat it as not matching
                        tracker.mismatchedRecipe();
                    } else {
                        //Calculate the current max based on the item input
                        itemInputHandler.calculateOperationsCanSupport(tracker, recipeItem);
                        if (tracker.shouldContinueChecking()) {
                            //Calculate the current max based on the fluid input
                            gasFirstInputHandler.calculateOperationsCanSupport(tracker, firstGasRecipeInput);
                            if (tracker.shouldContinueChecking()) {
                                //Calculate the current max based on the chemical input
                                gasSecondInputHandler.calculateOperationsCanSupport(tracker, secondGasRecipeInput);
                                if (tracker.shouldContinueChecking()) {
                                    output = recipe.getOutput(recipeItem, firstGasRecipeInput, secondGasRecipeInput);
                                    outputHandler.calculateOperationsCanSupport(tracker, output);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isInputValid() {
        ItemStack item = itemInputHandler.getInput();
        if (item.isEmpty()) {
            return false;
        }
        ChemicalStack firstChemical = gasFirstInputHandler.getInput();
        if (firstChemical.isEmpty()) {
            return false;
        }
        ChemicalStack secondChemical = gasSecondInputHandler.getInput();
        return !firstChemical.isEmpty() && recipe.test(item, firstChemical, secondChemical);
    }

    @Override
    protected void finishProcessing(int operations) {
        if (output != null && !recipeItem.isEmpty() && !firstGasRecipeInput.isEmpty() && !secondGasRecipeInput.isEmpty()) {
            itemInputHandler.use(recipeItem, operations);
            gasFirstInputHandler.use(firstGasRecipeInput, operations);
            gasSecondInputHandler.use(secondGasRecipeInput, operations);
            outputHandler.handleOutput(output, operations);
        }
    }
}
