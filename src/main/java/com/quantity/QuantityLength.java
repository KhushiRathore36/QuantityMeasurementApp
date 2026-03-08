package com.quantity;
import java.util.Objects;
public final class QuantityLength {
	private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public double toFeet() {
        return unit.toFeet(value);
    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {

        if (from == null || to == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        double feet = from.toFeet(value);
        return to.fromFeet(feet);
    }

    /* UC6 Addition (default unit = first operand) */
    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        double totalFeet = this.toFeet() + other.toFeet();

        double result = unit.fromFeet(totalFeet);

        return new QuantityLength(result, unit);
    }

    /* UC7 Addition with explicit target unit */

    public static QuantityLength add(
            QuantityLength q1,
            QuantityLength q2,
            LengthUnit targetUnit) {

        if (q1 == null || q2 == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double totalFeet = q1.toFeet() + q2.toFeet();

        double result = targetUnit.fromFeet(totalFeet);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

        double diff = Math.abs(this.toFeet() - other.toFeet());

        return diff < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toFeet());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
