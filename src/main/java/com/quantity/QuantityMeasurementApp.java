package com.quantity;

public class QuantityMeasurementApp {
	
	public static void main(String[] args) {

        // Length Example
        QuantityLength length1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12, LengthUnit.INCH);

        System.out.println("1 ft == 12 inch : " + length1.equals(length2));

        QuantityLength sum = length1.add(length2, LengthUnit.FEET);
        System.out.println("Sum in feet : " + sum);


        // Weight Example
        QuantityWeight weight1 = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight weight2 = new QuantityWeight(1000, WeightUnit.GRAM);

        System.out.println("1 kg == 1000 gram : " + weight1.equals(weight2));

        QuantityWeight weightSum = weight1.add(weight2, WeightUnit.KILOGRAM);
        System.out.println("Weight Sum : " + weightSum);
    }

}
