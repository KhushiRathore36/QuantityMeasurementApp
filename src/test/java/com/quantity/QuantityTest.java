package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityTest {
	private static final double EPSILON = 0.01;

    // ---------------- ADDITION TESTS ----------------

    @Test
    public void testAddition_SameUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(15.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(11.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // ---------------- SUBTRACTION TESTS ----------------

    @Test
    public void testSubtraction_SameUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ExplicitUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // ---------------- DIVISION TESTS ----------------

    @Test
    public void testDivision_SameUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testDivision_ByZero() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    // ---------------- CROSS CATEGORY ----------------

    @Test
    public void testCrossCategoryOperation() {

        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.add((Quantity) weight));
    }

    // ---------------- NULL TESTS ----------------

    @Test
    public void testNullOperand_Add() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(null));
    }

    @Test
    public void testNullOperand_Subtract() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract(null));
    }

    @Test
    public void testNullOperand_Divide() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.divide(null));
    }

    // ---------------- IMMUTABILITY ----------------

    @Test
    public void testImmutability_Add() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        q1.add(q2);

        assertEquals(10.0, q1.getValue());
    }

    @Test
    public void testImmutability_Subtract() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10.0, q1.getValue());
    }

    @Test
    public void testImmutability_Divide() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(10.0, q1.getValue());
    }
}