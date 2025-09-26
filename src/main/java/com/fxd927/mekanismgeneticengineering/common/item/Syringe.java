package com.fxd927.mekanismgeneticengineering.common.item;

import com.fxd927.mekanismgeneticengineering.common.MobDNAMapping;
import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import mekanism.api.Action;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.item.ItemGaugeDropper;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Syringe extends ItemGaugeDropper {

    public Syringe(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();
        if (!level.isClientSide && attacker instanceof Player player) {
            var mobId = target.getType().builtInRegistryHolder().key().location();
            Holder<Chemical> chemicalHolder = MobDNAMapping.getDNAForMob(mobId);

            if (chemicalHolder != null) {
                IChemicalHandler handler = Capabilities.CHEMICAL.getCapability(stack);
                if (handler != null && handler.getChemicalTanks() > 0) {
                    ChemicalStack chemStack = new ChemicalStack(chemicalHolder, 1000);
                    handler.insertChemical(chemStack, Action.EXECUTE);
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}