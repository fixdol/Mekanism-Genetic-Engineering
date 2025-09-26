package com.fxd927.mekanismgeneticengineering.common.registries;

import com.fxd927.mekanismgeneticengineering.common.MekanismGeneticEngineering;
import com.fxd927.mekanismgeneticengineering.common.item.Syringe;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.config.MekanismConfig;
import mekanism.common.item.ItemGaugeDropper;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;

public class MGEItems {

    private MGEItems() {
    }

    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(MekanismGeneticEngineering.MODID);

    public static final ItemRegistryObject<Item> COAL_TAR;
    public static final ItemRegistryObject<Syringe> SYRINGE;

    static {
        COAL_TAR = ITEMS.register("coal_tar", () -> new Item(new Item.Properties().stacksTo(64)));
        SYRINGE = ITEMS.registerItem("syringe", Syringe::new)
                .addAttachedContainerCapabilities(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder().addTank(ItemGaugeDropper.MERGED_TANK_CREATOR).build(), MekanismConfig.gear)
                .addAttachedContainerCapabilities(ContainerType.FLUID, () -> FluidTanksBuilder.builder().addTank(ItemGaugeDropper.MERGED_TANK_CREATOR).build(), MekanismConfig.gear);
    }

}
