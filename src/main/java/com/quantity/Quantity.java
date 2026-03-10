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

        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {

        unit.validateOperationSupport("subtraction");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        return new Quantity<>(
                unit.convertFromBaseUnit(resultBase),
                unit
        );
    }

    public Quantity<U> subtract(Quantity<U> other, U resultUnit) {

        unit.validateOperationSupport("subtraction");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double resultValue = resultUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(resultValue, resultUnit);
    }

    public double divide(Quantity<U> other) {

        unit.validateOperationSupport("division");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (Math.abs(base2) < EPSILON)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?>))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

	public Double getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
