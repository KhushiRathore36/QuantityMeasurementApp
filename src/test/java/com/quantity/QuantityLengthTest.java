package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {
	 private static final double EPSILON = 1e-6;

	   
	    @Test
	    public void testEquality_SameFeetValue() {

	        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
	        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

	        assertEquals(q1, q2);
	    }

	    @Test
	    public void testEquality_DifferentFeetValue() {

	        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
	        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

	        assertNotEquals(q1, q2);
	    }


	    @Test
	    public void testEquality_InchToInch() {

	        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
	        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

	        assertEquals(q1, q2);
	    }

	    @Test
	    public void testEquality_FeetToInch() {

	        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
	        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

	        assertEquals(feet, inch);
	    }

	    @Test
	    public void testEquality_YardToFeet() {

	        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
	        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

	        assertEquals(yard, feet);
	    }

	    @Test
	    public void testConversion_FeetToInches() {

	        double result = QuantityLength.convert(
	                1.0,
	                LengthUnit.FEET,
	                LengthUnit.INCH
	        );

	        assertEquals(12.0, result, EPSILON);
	    }

	    @Test
	    public void testConversion_InchesToFeet() {

	        double result = QuantityLength.convert(
	                24.0,
	                LengthUnit.INCH,
	                LengthUnit.FEET
	        );

	        assertEquals(2.0, result, EPSILON);
	    }

	    @Test
	    public void testConversion_YardsToFeet() {

	        double result = QuantityLength.convert(
	                3.0,
	                LengthUnit.YARDS,
	                LengthUnit.FEET
	        );

	        assertEquals(9.0, result, EPSILON);
	    }

	    @Test
	    public void testConversion_CentimeterToInch() {

	        double result = QuantityLength.convert(
	                2.54,
	                LengthUnit.CENTIMETERS,
	                LengthUnit.INCH
	        );

	        assertEquals(1.0, result, 1e-3);
	    }


	    @Test
	    public void testAddition_SameUnit_FeetPlusFeet() {

	        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
	        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

	        QuantityLength result = q1.add(q2);

	        assertEquals(
	                new QuantityLength(3.0, LengthUnit.FEET),
	                result
	        );
	    }

	    @Test
	    public void testAddition_CrossUnit_FeetPlusInch() {

	        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
	        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

	        QuantityLength result = feet.add(inch);

	        assertEquals(
	                new QuantityLength(2.0, LengthUnit.FEET),
	                result
	        );
	    }

	    @Test
	    public void testAddition_CrossUnit_InchPlusFeet() {

	        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
	        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

	        QuantityLength result = inch.add(feet);

	        assertEquals(
	                new QuantityLength(24.0, LengthUnit.INCH),
	                result
	        );
	    }

	    @Test
	    public void testAddition_WithZero() {

	        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
	        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCH);

	        QuantityLength result = q1.add(q2);

	        assertEquals(q1, result);
	    }

	    @Test
	    public void testAddition_NegativeValue() {

	        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
	        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

	        QuantityLength result = q1.add(q2);

	        assertEquals(
	                new QuantityLength(3.0, LengthUnit.FEET),
	                result
	        );
	    }

	    @Test
	    public void testAddition_NullOperand() {

	        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

	        assertThrows(IllegalArgumentException.class, () -> {
	            q1.add(null);
	        });
	    }

	    @Test
	    public void testConversion_InvalidUnit() {

	        assertThrows(IllegalArgumentException.class, () -> {
	            QuantityLength.convert(1.0, null, LengthUnit.FEET);
	        });
	    }

	    @Test
	    public void testConversion_NaNValue() {

	        assertThrows(IllegalArgumentException.class, () -> {
	            QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH);
	        });
	    }
}
