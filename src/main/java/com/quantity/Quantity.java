package com.quantity;
import java.util.Objects;
public class Quantity<U extends IMeasurable> {
	private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        converted = Math.round(converted * 100.0) / 100.0;

        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        double sumBase =
                unit.convertToBaseUnit(this.value) +
                other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double sumBase =
                unit.convertToBaseUnit(this.value) +
                other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> that = (Quantity<?>) obj;

        if (this.unit.getClass() != that.unit.getClass()) return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {

        double base = unit.convertToBaseUnit(value);

        return Objects.hash(base, unit.getClass());
    }

    @Override
    public String toString() {

        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
