package com.fxd927.mekanismgeneticengineering.api.recipes.outputs;

import com.fxd927.mekanismgeneticengineering.api.recipes.ChemicalToTripleChemicalRecipe;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.math.MathUtils;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@NothingNullByDefault
public class MGEOutputHelper {

    private MGEOutputHelper() {
    }

    // Java
    public static IOutputHandler<ChemicalToTripleChemicalRecipe.TripleChemicalRecipeOutput> getOutputHandler(
            IChemicalTank tank1, CachedRecipe.OperationTracker.RecipeError tank1NotEnoughSpaceError,
            IChemicalTank tank2, CachedRecipe.OperationTracker.RecipeError tank2NotEnoughSpaceError,
            IChemicalTank tank3, CachedRecipe.OperationTracker.RecipeError tank3NotEnoughSpaceError
    ) {
        Objects.requireNonNull(tank1, "Tank1 cannot be null.");
        Objects.requireNonNull(tank2, "Tank2 cannot be null.");
        Objects.requireNonNull(tank3, "Tank3 cannot be null.");
        Objects.requireNonNull(tank1NotEnoughSpaceError, "Tank1 not enough space error cannot be null.");
        Objects.requireNonNull(tank2NotEnoughSpaceError, "Tank2 not enough space error cannot be null.");
        Objects.requireNonNull(tank3NotEnoughSpaceError, "Tank3 not enough space error cannot be null.");
        return new IOutputHandler<>() {

            @Override
            public void handleOutput(ChemicalToTripleChemicalRecipe.TripleChemicalRecipeOutput toOutput, int operations) {
                if (!toOutput.firstChemical().isEmpty() && operations > 0) {
                    ChemicalStack output1 = toOutput.firstChemical().copyWithAmount(toOutput.firstChemical().getAmount() * operations);
                    tank1.insert(output1, Action.EXECUTE, AutomationType.INTERNAL);
                }
                if (!toOutput.secondChemical().isEmpty() && operations > 0) {
                    ChemicalStack output2 = toOutput.secondChemical().copyWithAmount(toOutput.secondChemical().getAmount() * operations);
                    tank2.insert(output2, Action.EXECUTE, AutomationType.INTERNAL);
                }
                if (!toOutput.thirdChemical().isEmpty() && operations > 0) {
                    ChemicalStack output3 = toOutput.thirdChemical().copyWithAmount(toOutput.thirdChemical().getAmount() * operations);
                    tank3.insert(output3, Action.EXECUTE, AutomationType.INTERNAL);
                }
            }

            @Override
            public void calculateOperationsCanSupport(CachedRecipe.OperationTracker tracker, ChemicalToTripleChemicalRecipe.TripleChemicalRecipeOutput toOutput) {
                if (!toOutput.firstChemical().isEmpty()) {
                    ChemicalStack maxOutput = toOutput.firstChemical().copyWithAmount(Long.MAX_VALUE);
                    ChemicalStack remainder = tank1.insert(maxOutput, Action.SIMULATE, AutomationType.INTERNAL);
                    long amountUsed = maxOutput.getAmount() - remainder.getAmount();
                    int operations = MathUtils.clampToInt(amountUsed / toOutput.firstChemical().getAmount());
                    tracker.updateOperations(operations);
                    if (operations == 0) {
                        if (amountUsed == 0 && tank1.getNeeded() > 0) {
                            tracker.addError(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
                        } else {
                            tracker.addError(tank1NotEnoughSpaceError);
                        }
                    }
                }
            }
        };
    }

    private static void handleOutput(IChemicalTank tank, ChemicalStack toOutput, int operations) {
        if (operations == 0) {
            //This should not happen
            return;
        }
        ChemicalStack output = toOutput.copyWithAmount(toOutput.getAmount() * operations);
        tank.insert(output, Action.EXECUTE, AutomationType.INTERNAL);
    }

    private static void handleOutput(IExtendedFluidTank fluidTank, FluidStack toOutput, int operations) {
        if (operations == 0) {
            //This should not happen
            return;
        }
        fluidTank.insert(toOutput.copyWithAmount(toOutput.getAmount() * operations), Action.EXECUTE, AutomationType.INTERNAL);
    }

    private static void handleOutput(IInventorySlot inventorySlot, ItemStack toOutput, int operations) {
        if (operations == 0 || toOutput.isEmpty()) {
            return;
        }
        ItemStack output = toOutput.copy();
        if (operations > 1) {
            //If we are doing more than one operation we need to make a copy of our stack and change the amount
            // that we are using the fill the tank with
            output.setCount(output.getCount() * operations);
        }
        inventorySlot.insertItem(output, Action.EXECUTE, AutomationType.INTERNAL);
    }

    /**
     * Calculates how many operations the output has room for and updates the given operation tracker.
     *
     * @param tracker        Tracker of current errors and max operations.
     * @param tank           Output.
     * @param toOutput       Output result.
     * @param notEnoughSpace The error to apply if the output causes the recipe to not be able to perform any operations.
     */
    private static void calculateOperationsCanSupport(CachedRecipe.OperationTracker tracker, CachedRecipe.OperationTracker.RecipeError notEnoughSpace, IChemicalTank tank,
                                                      ChemicalStack toOutput) {
        //If our output is empty, we have nothing to add, so we treat it as being able to fit all
        if (!toOutput.isEmpty()) {
            //Copy the stack and make it be max size
            ChemicalStack maxOutput = toOutput.copyWithAmount(Long.MAX_VALUE);
            //Divide the amount we can actually use by the amount one output operation is equal to, capping it at the max we were told about
            ChemicalStack remainder = tank.insert(maxOutput, Action.SIMULATE, AutomationType.INTERNAL);
            long amountUsed = maxOutput.getAmount() - remainder.getAmount();
            //Divide the amount we can actually use by the amount one output operation is equal to, capping it at the max we were told about
            int operations = MathUtils.clampToInt(amountUsed / toOutput.getAmount());
            tracker.updateOperations(operations);
            if (operations == 0) {
                if (amountUsed == 0 && tank.getNeeded() > 0) {
                    tracker.addError(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
                } else {
                    tracker.addError(notEnoughSpace);
                }
            }
        }
    }

    private static void calculateOperationsCanSupport(CachedRecipe.OperationTracker tracker, CachedRecipe.OperationTracker.RecipeError notEnoughSpace, IExtendedFluidTank tank, FluidStack toOutput) {
        //If our output is empty, we have nothing to add, so we treat it as being able to fit all
        if (!toOutput.isEmpty()) {
            //Copy the stack and make it be max size
            FluidStack maxOutput = toOutput.copyWithAmount(Integer.MAX_VALUE);
            //Then simulate filling the fluid tank, so we can see how much actually can fit
            FluidStack remainder = tank.insert(maxOutput, Action.SIMULATE, AutomationType.INTERNAL);
            int amountUsed = maxOutput.getAmount() - remainder.getAmount();
            //Divide the amount we can actually use by the amount one output operation is equal to, capping it at the max we were told about
            int operations = amountUsed / toOutput.getAmount();
            tracker.updateOperations(operations);
            if (operations == 0) {
                if (amountUsed == 0 && tank.getNeeded() > 0) {
                    tracker.addError(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
                } else {
                    tracker.addError(notEnoughSpace);
                }
            }
        }
    }

    private static void calculateOperationsCanSupport(CachedRecipe.OperationTracker tracker, CachedRecipe.OperationTracker.RecipeError notEnoughSpace, IInventorySlot slot, ItemStack toOutput) {
        //If our output is empty, we have nothing to add, so we treat it as being able to fit all
        if (!toOutput.isEmpty()) {
            //Make a copy of the stack we are outputting with its maximum size
            ItemStack output = toOutput.copyWithCount(toOutput.getMaxStackSize());
            ItemStack remainder = slot.insertItem(output, Action.SIMULATE, AutomationType.INTERNAL);
            int amountUsed = output.getCount() - remainder.getCount();
            //Divide the amount we can actually use by the amount one output operation is equal to, capping it at the max we were told about
            int operations = amountUsed / toOutput.getCount();
            tracker.updateOperations(operations);
            if (operations == 0) {
                if (amountUsed == 0 && slot.getLimit(slot.getStack()) - slot.getCount() > 0) {
                    tracker.addError(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
                } else {
                    tracker.addError(notEnoughSpace);
                }
            }
        }
    }
}
