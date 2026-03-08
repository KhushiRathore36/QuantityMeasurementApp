package com.quantity;

public class QuantityMeasurementApp {
	
	public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(
                QuantityLength.add(q1, q2, LengthUnit.FEET));

        System.out.println(
                QuantityLength.add(q1, q2, LengthUnit.INCHES));

        System.out.println(
                QuantityLength.add(q1, q2, LengthUnit.YARDS));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q4 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(
                QuantityLength.add(q3, q4, LengthUnit.YARDS));

        QuantityLength q5 = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength q6 = new QuantityLength(1.0, LengthUnit.YARDS);

        System.out.println(
                QuantityLength.add(q5, q6, LengthUnit.FEET));

        QuantityLength q7 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength q8 = new QuantityLength(1.0, LengthUnit.INCHES);

        System.out.println(
                QuantityLength.add(q7, q8, LengthUnit.CENTIMETERS));
    }

}
