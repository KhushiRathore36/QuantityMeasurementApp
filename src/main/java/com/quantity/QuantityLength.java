package com.quantity;
import java.util.Objects;
public final class QuantityLength {
	 private double value;
	    private LengthUnit unit;

	    public QuantityLength(double value, LengthUnit unit) {
	        if (unit == null) {
	            throw new IllegalArgumentException("Unit cannot be null");
	        }
	        this.value = value;
	        this.unit = unit;
	    }

	    public double toBase() {
	        return unit.toBase(value);
	    }

	    public static double convert(double value, LengthUnit from, LengthUnit to) {
	        if (from == null || to == null) {
	            throw new IllegalArgumentException("Unit cannot be null");
	        }

	        double base = from.toBase(value);
	        return to.fromBase(base);
	    }

	    public QuantityLength add(QuantityLength other, LengthUnit resultUnit) {
	        double sumBase = this.toBase() + other.toBase();
	        double result = resultUnit.fromBase(sumBase);
	        return new QuantityLength(result, resultUnit);
	    }

	    @Override
	    public boolean equals(Object obj) {

	        if (this == obj) return true;

	        if (obj == null || getClass() != obj.getClass()) return false;

	        QuantityLength other = (QuantityLength) obj;

	        return Double.compare(this.toBase(), other.toBase()) == 0;
	    }

	    @Override
	    public String toString() {
	        return value + " " + unit;
	    }
}
