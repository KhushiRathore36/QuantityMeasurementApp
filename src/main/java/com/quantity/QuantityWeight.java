package com.quantity;

public class QuantityWeight {
	private double value;
    private WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double toBase() {
        return unit.toBase(value);
    }

    public static double convert(double value, WeightUnit from, WeightUnit to) {

        if (from == null || to == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double base = from.toBase(value);
        return to.fromBase(base);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit resultUnit) {

        double sumBase = this.toBase() + other.toBase();
        double result = resultUnit.fromBase(sumBase);

        return new QuantityWeight(result, resultUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
