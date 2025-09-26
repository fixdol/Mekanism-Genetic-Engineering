package com.fxd927.mekanismgeneticengineering.common.attachments.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import mekanism.api.RelativeSide;
import mekanism.api.SerializationConstants;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.IPersistentConfigInfo;
import net.minecraft.Util;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

@NothingNullByDefault
public record MGEAttachedSideConfig(Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo) {
    public static final AttachedSideConfig NUCLEAR_ACID_CENTRIFUGE = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.CHEMICAL, LightConfigInfo.TWO_INPUT_ONE_OUTPUT);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        return new AttachedSideConfig(configInfo);
    });

    public static final AttachedSideConfig GENE_ANALYZER = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.CHEMICAL, LightConfigInfo.ONE_INPUT_THREE_OUTPUT);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        return new AttachedSideConfig(configInfo);
    });

    public record LightConfigInfo(Map<RelativeSide, DataType> sideConfig,
                                  boolean isEjecting) implements IPersistentConfigInfo {
        public static final AttachedSideConfig.LightConfigInfo ONE_INPUT_THREE_OUTPUT = Util.make(() -> {
            Map<RelativeSide, DataType> sideConfig = new EnumMap<>(RelativeSide.class);
            sideConfig.put(RelativeSide.LEFT, DataType.INPUT);
            sideConfig.put(RelativeSide.RIGHT, DataType.OUTPUT_1);
            sideConfig.put(RelativeSide.FRONT, DataType.OUTPUT_2);
            sideConfig.put(RelativeSide.TOP, DataType.OUTPUT);
            sideConfig.put(RelativeSide.BACK, DataType.ENERGY);
            return new AttachedSideConfig.LightConfigInfo(sideConfig, true);
        });

        public static final AttachedSideConfig.LightConfigInfo TWO_INPUT_ONE_OUTPUT = Util.make(() -> {
            Map<RelativeSide, DataType> sideConfig = new EnumMap<>(RelativeSide.class);
            sideConfig.put(RelativeSide.LEFT, DataType.OUTPUT);
            sideConfig.put(RelativeSide.TOP, DataType.OUTPUT_1);
            sideConfig.put(RelativeSide.RIGHT, DataType.OUTPUT_2);
            sideConfig.put(RelativeSide.BACK, DataType.ENERGY);
            return new AttachedSideConfig.LightConfigInfo(sideConfig, true);
        });

        @NotNull
        @Override
        public DataType getDataType(@NotNull RelativeSide side) {
            return sideConfig.getOrDefault(side, DataType.NONE);
        }
    }
}
