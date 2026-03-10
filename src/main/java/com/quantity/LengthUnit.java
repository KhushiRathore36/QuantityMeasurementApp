package com.quantity;

public enum LengthUnit implements IMeasurable{
	INCHES(1.0),
    FEET(12.0),
    YARD(36.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}
