package com.quantity;

public class QuantityMeasurementApp {
	public static void demonstrateEquality(Quantity<?> q1, Quantity<?> q2) {
        System.out.println(q1 + " == " + q2 + " : " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        System.out.println(quantity + " -> " + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U resultUnit) {

        System.out.println("Addition Result: " + q1.add(q2, resultUnit));
    }

    public static void main(String[] args) {

        // LENGTH DEMO
        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCHES);
        demonstrateAddition(length1, length2, LengthUnit.FEET);

        // WEIGHT DEMO
        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);
        demonstrateConversion(weight1, WeightUnit.GRAM);
        demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM);

        // VOLUME DEMO (UC11)
        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        demonstrateEquality(volume1, volume2);
        demonstrateConversion(volume1, VolumeUnit.MILLILITRE);
        demonstrateAddition(volume1, volume2, VolumeUnit.LITRE);

        demonstrateConversion(volume3, VolumeUnit.LITRE);
        demonstrateAddition(volume1, volume3, VolumeUnit.MILLILITRE);
    }
	

}
