package com.quantity;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
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

    // ---------------------------------------------------------
    // ENUM FOR ARITHMETIC OPERATIONS (UC13)
    // ---------------------------------------------------------

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON)
                throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // ---------------------------------------------------------
    // CENTRALIZED VALIDATION (UC13)
    // ---------------------------------------------------------

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (Double.isNaN(other.value) || Double.isInfinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // ---------------------------------------------------------
    // CORE HELPER METHOD (UC13)
    // ---------------------------------------------------------

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double baseA = unit.convertToBaseUnit(value);
        double baseB = other.unit.convertToBaseUnit(other.value);

        return operation.compute(baseA, baseB);
    }

    // ---------------------------------------------------------
    // ROUNDING
    // ---------------------------------------------------------

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ---------------------------------------------------------
    // ADDITION
    // ---------------------------------------------------------

    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double converted = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------------------------------------------------------
    // SUBTRACTION
    // ---------------------------------------------------------

    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double converted = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------------------------------------------------------
    // DIVISION
    // ---------------------------------------------------------

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ---------------------------------------------------------
    // CONVERSION
    // ---------------------------------------------------------

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------------------------------------------------------
    // EQUALITY
    // ---------------------------------------------------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double a = unit.convertToBaseUnit(value);
        double b = other.unit.convertToBaseUnit(other.value);

        return Math.abs(a - b) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(unit.convertToBaseUnit(value)));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
