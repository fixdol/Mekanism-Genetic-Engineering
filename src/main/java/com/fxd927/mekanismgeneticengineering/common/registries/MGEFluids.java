package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MGEChemicalConstants;
import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import mekanism.common.ChemicalConstants;
import mekanism.common.Mekanism;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class MGEFluids {
    private MGEFluids() {
    }

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> METHANE = FLUIDS.registerLiquidChemical(MGEChemicalConstants.METHANE);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> CHLOROFORM = FLUIDS.registerLiquidChemical(MGEChemicalConstants.CHLOROFORM);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> PHENOL = FLUIDS.registerLiquidChemical(MGEChemicalConstants.PHENOL);

}
