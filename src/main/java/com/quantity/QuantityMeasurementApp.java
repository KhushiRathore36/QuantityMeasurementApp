package com.quantity;

public class QuantityMeasurementApp {
	
	public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength converted =
                q1.convertTo(LengthUnit.INCHES);

        System.out.println("Convert: " + converted);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength sum =
                q1.add(q2, LengthUnit.FEET);

        System.out.println("Addition: " + sum);

        QuantityLength q3 =
                new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength q4 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        System.out.println("Equality: " + q3.equals(q4));

        QuantityLength q5 =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        System.out.println("CM to Inches: " +
                q5.convertTo(LengthUnit.INCHES));
    }

}
