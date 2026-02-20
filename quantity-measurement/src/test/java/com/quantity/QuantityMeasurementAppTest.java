package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {
	
	@Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2),
                "1.0 feet should equal 1.0 feet");
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2),
                "1.0 inch should equal 1.0 inch");
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2),
                "1 foot should equal 12 inches");
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2),
                "12 inches should equal 1 foot");
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(q2),
                "1 foot should not equal 2 feet");
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertFalse(q1.equals(q2),
                "1 inch should not equal 2 inches");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q),
                "Object should equal itself");
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null),
                "Object should not equal null");
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(1.0, null)
        );
    }

    @Test
    void testEquality_DifferentObjectType() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals("Some String"),
                "Quantity should not equal different object type");
    }
}
