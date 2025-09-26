package com.fxd927.mekanismgeneticengineering.common;

import mekanism.common.base.IChemicalConstant;

public enum MGEChemicalConstants implements IChemicalConstant {

    METHANE("methane", 0xFFFFFFFF, 0, 20.28F, 70.85F),
    CHLOROFORM("chloroform", 0xFF6CE2FF, 0, 90.19F, 1_141),
    PHENOL("phenol", 0xFF6CE2FF, 0, 90.19F, 1_141);

    private final String name;
    private final int color;
    private final int lightLevel;
    private final float temperature;
    private final float density;

    /**
     * @param name        The name of the chemical
     * @param color       Visual color in ARGB format
     * @param lightLevel  Light level
     * @param temperature Temperature in Kelvin that the chemical exists as a liquid
     * @param density     Density as a liquid in kg/m^3
     */
    MGEChemicalConstants(String name, int color, int lightLevel, float temperature, float density) {
        this.name = name;
        this.color = color;
        this.lightLevel = lightLevel;
        this.temperature = temperature;
        this.density = density;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public float getTemperature() {
        return temperature;
    }

    @Override
    public float getDensity() {
        return density;
    }

    @Override
    public int getLightLevel() {
        return lightLevel;
    }
}
