package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {
	private static final double EPSILON = 0.01;

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {

        double result =
                LengthUnit.INCHES.convertToBaseUnit(12.0);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {

        double result =
                LengthUnit.INCHES.convertFromBaseUnit(1.0);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void testQuantityLengthRefactored_Equality() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {

        QuantityLength q =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    public void testQuantityLengthRefactored_Add() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.667,
                result.getValue(),
                0.01);
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}
