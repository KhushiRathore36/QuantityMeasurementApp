package com.quantity;

public enum LengthUnit implements IMeasurable{
	FEET(12),
    INCHES(1),
    YARDS(36),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double factor) {
        this.conversionFactor = factor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}
