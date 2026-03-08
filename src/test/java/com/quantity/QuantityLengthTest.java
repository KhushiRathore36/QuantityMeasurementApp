package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {
	private static final double EPSILON = 0.01;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(q1, q2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(q1, q2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength r1 =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        QuantityLength r2 =
                QuantityLength.add(b, a, LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    public void testAddition_WithZero() {

        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(q1, q2, LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NullTargetUnit() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(q1, q2, null));
    }

    @Test
    public void testAddition_NegativeValues() {

        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(q1, q2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }
}
