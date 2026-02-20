package com.quantity;

public class QuantityMeasurementApp {
	
	public enum LengthUnit {

	     FEET(1.0),
	     INCH(1.0 / 12.0),                 
	     YARDS(3.0),                       
	     CENTIMETERS(0.393701 / 12.0);    

	     private final double conversionFactorToFeet;

	     LengthUnit(double conversionFactorToFeet) {
	         this.conversionFactorToFeet = conversionFactorToFeet;
	     }

	     public double toFeet(double value) {
	         return value * conversionFactorToFeet;
	     }
	 }

	 public static class QuantityLength {

	     private final double value;
	     private final LengthUnit unit;

	     public QuantityLength(double value, LengthUnit unit) {
	         if (unit == null) {
	             throw new IllegalArgumentException("Unit cannot be null");
	         }
	         this.value = value;
	         this.unit = unit;
	     }

	     public double getValueInFeet() {
	         return unit.toFeet(value);
	     }

	     @Override
	     public boolean equals(Object obj) {

	         if (this == obj) return true;
	         if (obj == null || getClass() != obj.getClass()) return false;

	         QuantityLength other = (QuantityLength) obj;

	         return Double.compare(
	                 this.getValueInFeet(),
	                 other.getValueInFeet()
	         ) == 0;
	     }

	     @Override
	     public int hashCode() {
	         return Double.hashCode(getValueInFeet());
	     }

	     @Override
	     public String toString() {
	         return "Quantity(" + value + ", " + unit + ")";
	     }
	 }

	 public static void main(String[] args) {

	     QuantityLength q1 =
	             new QuantityLength(1.0, LengthUnit.YARDS);

	     QuantityLength q2 =
	             new QuantityLength(3.0, LengthUnit.FEET);

	     System.out.println("Input: " + q1 + " and " + q2);
	     System.out.println("Output: Equal (" + q1.equals(q2) + ")");
	 }

}
