package com.fxd927.mekanismgeneticengineering.common;

import com.fxd927.mekanismgeneticengineering.common.registries.MGEChemicals;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public enum MobDNAMapping {
    ALLAY(EntityType.ALLAY, MGEChemicals.ALLAY_DNA.get()),
    ARMADILLO(EntityType.ARMADILLO, MGEChemicals.ARMADILLO_DNA.get()),
    AXOLOTL(EntityType.AXOLOTL, MGEChemicals.AXOLOTL_DNA.get()),
    BAT(EntityType.BAT, MGEChemicals.BAT_DNA.get()),
    BEE(EntityType.BEE, MGEChemicals.BEE_DNA.get()),
    BLAZE(EntityType.BLAZE, MGEChemicals.BLAZE_DNA.get()),
    BOGGED(EntityType.BOGGED, MGEChemicals.BOGGED_DNA.get()),
    BREEZE(EntityType.BREEZE, MGEChemicals.BREEZE_DNA.get()),
    CAMEL(EntityType.CAMEL, MGEChemicals.CAMEL_DNA.get()),
    CAT(EntityType.CAT, MGEChemicals.CAT_DNA.get()),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, MGEChemicals.CAVE_SPIDER_DNA.get()),
    CHICKEN(EntityType.CHICKEN, MGEChemicals.CHICKEN_DNA.get()),
    COD(EntityType.COD, MGEChemicals.COD_DNA.get()),
    COW(EntityType.COW, MGEChemicals.COW_DNA.get()),
    CREEPER(EntityType.CREEPER, MGEChemicals.CREEPER_DNA.get()),
    DOLPHIN(EntityType.DOLPHIN, MGEChemicals.DOLPHIN_DNA.get()),
    DONKEY(EntityType.DONKEY, MGEChemicals.DONKEY_DNA.get()),
    DROWNED(EntityType.DROWNED, MGEChemicals.DROWNED_DNA.get()),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, MGEChemicals.ELDER_GUARDIAN_DNA.get()),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, MGEChemicals.ENDER_DRAGON_DNA.get()),
    ENDERMAN(EntityType.ENDERMAN, MGEChemicals.ENDERMAN_DNA.get()),
    ENDERMITE(EntityType.ENDERMITE, MGEChemicals.ENDERMITE_DNA.get()),
    EVOKER(EntityType.EVOKER, MGEChemicals.EVOKER_DNA.get()),
    FOX(EntityType.FOX, MGEChemicals.FOX_DNA.get()),
    FROG(EntityType.FROG, MGEChemicals.FROG_DNA.get()),
    GHAST(EntityType.GHAST, MGEChemicals.GHAST_DNA.get()),
    GLOW_SQUID(EntityType.GLOW_SQUID, MGEChemicals.GLOW_SQUID_DNA.get()),
    GOAT(EntityType.GOAT, MGEChemicals.GOAT_DNA.get()),
    GUARDIAN(EntityType.GUARDIAN, MGEChemicals.GUARDIAN_DNA.get()),
    HOGLIN(EntityType.HOGLIN, MGEChemicals.HOGLIN_DNA.get()),
    HORSE(EntityType.HORSE, MGEChemicals.HORSE_DNA.get()),
    HUSK(EntityType.HUSK, MGEChemicals.HUSK_DNA.get()),
    LLAMA(EntityType.LLAMA, MGEChemicals.LLAMA_DNA.get()),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, MGEChemicals.MAGMA_CUBE_DNA.get()),
    MOOSHROOM(EntityType.MOOSHROOM, MGEChemicals.MOOSHROOM_DNA.get()),
    MULE(EntityType.MULE, MGEChemicals.MULE_DNA.get()),
    OCELOT(EntityType.OCELOT, MGEChemicals.OCELOT_DNA.get()),
    PANDA(EntityType.PANDA, MGEChemicals.PANDA_DNA.get()),
    PARROT(EntityType.PARROT, MGEChemicals.PARROT_DNA.get()),
    PHANTOM(EntityType.PHANTOM, MGEChemicals.PHANTOM_DNA.get()),
    PIG(EntityType.PIG, MGEChemicals.PIG_DNA.get()),
    PIGLIN(EntityType.PIGLIN, MGEChemicals.PIGLIN_DNA.get()),
    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, MGEChemicals.PIGLIN_DNA.get()),
    PILLAGER(EntityType.PILLAGER, MGEChemicals.PILLAGER_DNA.get()),
    POLAR_BEAR(EntityType.POLAR_BEAR, MGEChemicals.POLAR_BEAR_DNA.get()),
    PUFFERFISH(EntityType.PUFFERFISH, MGEChemicals.PUFFERFISH_DNA.get()),
    RABBIT(EntityType.RABBIT, MGEChemicals.RABBIT_DNA.get()),
    RAVAGER(EntityType.RAVAGER, MGEChemicals.RAVAGER_DNA.get()),
    SALMON(EntityType.SALMON, MGEChemicals.SALMON_DNA.get()),
    SHEEP(EntityType.SHEEP, MGEChemicals.SHEEP_DNA.get()),
    SHULKER(EntityType.SHULKER, MGEChemicals.SHULKER_DNA.get()),
    SILVERFISH(EntityType.SILVERFISH, MGEChemicals.SILVERFISH_DNA.get()),
    SKELETON(EntityType.SKELETON, MGEChemicals.SKELETON_DNA.get()),
    SLIME(EntityType.SLIME, MGEChemicals.SLIME_DNA.get()),
    SNIFFER(EntityType.SNIFFER, MGEChemicals.SNIFFER_DNA.get()),
    SPIDER(EntityType.SPIDER, MGEChemicals.SPIDER_DNA.get()),
    SQUID(EntityType.SQUID, MGEChemicals.SQUID_DNA.get()),
    STRAY(EntityType.STRAY, MGEChemicals.STRAY_DNA.get()),
    STRIDER(EntityType.STRIDER, MGEChemicals.STRIDER_DNA.get()),
    TADPOLE(EntityType.TADPOLE, MGEChemicals.FROG_DNA.get()),
    TRADER_LLAMA(EntityType.TRADER_LLAMA, MGEChemicals.LLAMA_DNA.get()),
    TROPICAL_FISH(EntityType.TROPICAL_FISH, MGEChemicals.TROPICAL_FISH_DNA.get()),
    TURTLE(EntityType.TURTLE, MGEChemicals.TURTLE_DNA.get()),
    VEX(EntityType.VEX, MGEChemicals.VEX_DNA.get()),
    VILLAGER(EntityType.VILLAGER, MGEChemicals.VILLAGER_DNA.get()),
    VINDICATOR(EntityType.VINDICATOR, MGEChemicals.VINDICATOR_DNA.get()),
    WANDERING_TRADER(EntityType.WANDERING_TRADER, MGEChemicals.VILLAGER_DNA.get()),
    WARDEN(EntityType.WARDEN, MGEChemicals.WARDEN_DNA.get()),
    WITCH(EntityType.WITCH, MGEChemicals.WITCH_DNA.get()),
    WITHER(EntityType.WITHER, MGEChemicals.WITHER_DNA.get()),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, MGEChemicals.WITHER_SKELETON_DNA.get()),
    WOLF(EntityType.WOLF, MGEChemicals.WOLF_DNA.get()),
    ZOGLIN(EntityType.ZOGLIN, MGEChemicals.ZOGLIN_DNA.get()),
    ZOMBIE(EntityType.ZOMBIE, MGEChemicals.ZOMBIE_DNA.get()),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, MGEChemicals.ZOMBIE_VILLAGER_DNA.get()),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, MGEChemicals.ZOMBIFIED_PIGLIN_DNA.get());

    private final EntityType<?> entityType;
    private final Holder<Chemical> chemicalHolder;

    MobDNAMapping(EntityType<?> entityType, Chemical chemical) {
        this.entityType = entityType;
        this.chemicalHolder = MekanismAPI.CHEMICAL_REGISTRY.wrapAsHolder(chemical);
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public Holder<Chemical> getChemicalHolder() {
        return chemicalHolder;
    }

    public static Holder<Chemical> getDNAForMob(EntityType<?> type) {
        for (MobDNAMapping mapping : values()) {
            if (mapping.entityType == type) {
                return mapping.chemicalHolder;
            }
        }
        return null;
    }

    public static ResourceLocation getEntityId(EntityType<?> type) {
        return EntityType.getKey(type);
    }
}
