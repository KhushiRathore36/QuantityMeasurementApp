package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {
	 @Test
	    public void testEquality_SameValue() {

	        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
	        QuantityLength b = new QuantityLength(12, LengthUnit.INCH);

	        assertTrue(a.equals(b));
	    }

	    @Test
	    public void testEquality_DifferentValue() {

	        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
	        QuantityLength b = new QuantityLength(2, LengthUnit.FEET);

	        assertFalse(a.equals(b));
	    }

	    @Test
	    public void testConversion() {

	        double result = QuantityLength.convert(1, LengthUnit.FEET, LengthUnit.INCH);

	        assertEquals(12, result);
	    }

	    @Test
	    public void testAddition() {

	        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
	        QuantityLength b = new QuantityLength(12, LengthUnit.INCH);

	        QuantityLength result = a.add(b, LengthUnit.FEET);

	        assertEquals(new QuantityLength(2, LengthUnit.FEET), result);
	    }

	    @Test
	    public void testNullUnit() {

	        assertThrows(IllegalArgumentException.class, () -> {
	            new QuantityLength(1.0, null);
	        });
	    }
}
