package com.quantity;
import java.util.Objects;
public class Quantity<U extends IMeasurable> {
	private final double value;
    private final U unit;
    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private void validate(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ---------- ADDITION ----------
    public Quantity<U> add(Quantity<U> other) {
        validate(other);

        double result = this.toBase() + other.toBase();
        double converted = unit.convertFromBaseUnit(result);

        return new Quantity<>(round(converted), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other);
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double result = this.toBase() + other.toBase();
        double converted = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------- SUBTRACTION (UC12) ----------
    public Quantity<U> subtract(Quantity<U> other) {
        validate(other);

        double result = this.toBase() - other.toBase();
        double converted = unit.convertFromBaseUnit(result);

        return new Quantity<>(round(converted), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double result = this.toBase() - other.toBase();
        double converted = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------- DIVISION (UC12) ----------
    public double divide(Quantity<U> other) {
        validate(other);

        double divisor = other.toBase();

        if (Math.abs(divisor) < EPSILON)
            throw new ArithmeticException("Division by zero");

        return this.toBase() / divisor;
    }

    // ---------- CONVERSION ----------
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = this.toBase();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------- EQUALITY ----------
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double a = this.unit.convertToBaseUnit(this.value);
        double b = other.unit.convertToBaseUnit(other.value);

        return Math.abs(a - b) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(toBase()));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
