package com.quantity;

public enum WeightUnit {
	GRAM(1),
    KILOGRAM(1000);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double toBase(double value) {
        return value * conversionFactor;
    }

    public double fromBase(double baseValue) {
        return baseValue / conversionFactor;
    }
}
