package com.quantity;

public class QuantityMeasurementApp {
	
	public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),                 
        YARDS(3.0),                      
        CENTIMETERS(0.393701 / 12.0);    

        private final double factorToFeet;

        LengthUnit(double factorToFeet) {
            this.factorToFeet = factorToFeet;
        }

        public double toFeet(double value) {
            return value * factorToFeet;
        }

        public double fromFeet(double feetValue) {
            return feetValue / factorToFeet;
        }

        public double getFactor() {
            return factorToFeet;
        }
    }

    public static final class QuantityLength {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

       

        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            validate(value, source);
            if (target == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            if (source == target)
                return value;

            double valueInFeet = source.toFeet(value);
            return target.fromFeet(valueInFeet);
        }


        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue =
                    convert(this.value, this.unit, targetUnit);

            return new QuantityLength(convertedValue, targetUnit);
        }

        private static void validate(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite number");
        }

        private double toBaseFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(this.toBaseFeet()
                    - other.toBaseFeet()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println("convert(1.0, FEET, INCH) = " +
                QuantityLength.convert(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCH));

        System.out.println("convert(3.0, YARDS, FEET) = " +
                QuantityLength.convert(3.0,
                        LengthUnit.YARDS,
                        LengthUnit.FEET));

        System.out.println("convert(36.0, INCH, YARDS) = " +
                QuantityLength.convert(36.0,
                        LengthUnit.INCH,
                        LengthUnit.YARDS));
    }

}
