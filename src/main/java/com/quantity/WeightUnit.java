package com.quantity;

public enum WeightUnit implements IMeasurable{
	GRAM(1),
    KILOGRAM(1000);

    private final double conversionFactor;

    WeightUnit(double factor) {
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
