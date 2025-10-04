package com.fxd927.mekanismgeneticengineering.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class CustomMobDNALoader extends SimpleJsonResourceReloadListener {

    private static final Map<EntityType<?>, Holder<Chemical>> CUSTOM_MAPPINGS = new HashMap<>();

    public CustomMobDNALoader() {
        super(new com.google.gson.Gson(), "mob_dna");
        // /data/<modid>/mob_dna/*.json を探索
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        CUSTOM_MAPPINGS.clear();

        for (var entry : jsons.entrySet()) {
            JsonObject obj = entry.getValue().getAsJsonObject();
            String entityId = obj.get("entity").getAsString();
            String chemicalId = obj.get("chemical").getAsString();

            EntityType<?> entity = EntityType.byString(entityId).orElse(null);

            ResourceLocation chemRL;
            try {
                String[] split = chemicalId.split(":", 2);
                if (split.length == 2) {
                    chemRL = ResourceLocation.fromNamespaceAndPath(split[0], split[1]);
                } else {
                    chemRL = ResourceLocation.fromNamespaceAndPath(MekanismGeneticEngineering.MODID, split[0]);
                }
            } catch (Exception e) {
                continue;
            }

            Chemical chem = MekanismAPI.CHEMICAL_REGISTRY.get(chemRL);

            if (entity != null && chem != null) {
                CUSTOM_MAPPINGS.put(entity, MekanismAPI.CHEMICAL_REGISTRY.wrapAsHolder(chem));
            }
        }
    }

    public static Holder<Chemical> getCustomDNA(EntityType<?> type) {
        return CUSTOM_MAPPINGS.get(type);
    }
}