package com.fxd927.mekanismgeneticengineering.common;

import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;

public enum MobDNAMapping {
    COD("minecraft:cod", MGEChemicals.COD_DNA.get()),
    CREEPER("minecraft:creeper", MGEChemicals.CREEPER_DNA.get()),
    WITHER("minecraft:wither", MGEChemicals.WITHER_DNA.get()),
    WITHER_SKELETON("wither_skeleton", MGEChemicals.WITHER_SKELETON_DNA.get()),
    ZOMBIE("minecraft:zombie", MGEChemicals.ZOMBIE_DNA.get());

    private final String mobId;
    private final Holder<Chemical> chemicalHolder;

    MobDNAMapping(String mobId, Chemical chemical) {
        this.mobId = mobId;
        this.chemicalHolder = MekanismAPI.CHEMICAL_REGISTRY.wrapAsHolder(chemical);
    }

    public String getMobId() {
        return mobId;
    }

    public Holder<Chemical> getChemicalHolder() {
        return chemicalHolder;
    }

    public static Holder<Chemical> getDNAForMob(ResourceLocation mobId) {
        for (MobDNAMapping mapping : values()) {
            if (mapping.mobId.equals(mobId.toString())) {
                return mapping.getChemicalHolder();
            }
        }
        return null;
    }
}
