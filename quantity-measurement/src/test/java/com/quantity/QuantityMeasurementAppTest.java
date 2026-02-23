package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

	 private static final double EPSILON = 1e-6;

	    @Test
	    public void testFeetEquality() {

	        QuantityMeasurementApp.QuantityLength q1 =
	                new QuantityMeasurementApp.QuantityLength(
	                        1.0,
	                        QuantityMeasurementApp.LengthUnit.FEET);

	        QuantityMeasurementApp.QuantityLength q2 =
	                new QuantityMeasurementApp.QuantityLength(
	                        1.0,
	                        QuantityMeasurementApp.LengthUnit.FEET);

	        assertEquals(q1, q2);
	    }

	    @Test
	    public void testFeetToInchEquality() {

	        QuantityMeasurementApp.QuantityLength feet =
	                new QuantityMeasurementApp.QuantityLength(
	                        1.0,
	                        QuantityMeasurementApp.LengthUnit.FEET);

	        QuantityMeasurementApp.QuantityLength inch =
	                new QuantityMeasurementApp.QuantityLength(
	                        12.0,
	                        QuantityMeasurementApp.LengthUnit.INCH);

	        assertEquals(feet, inch);
	    }


	    @Test
	    public void testConversion_FeetToInch() {

	        double result =
	                QuantityMeasurementApp.QuantityLength.convert(
	                        1.0,
	                        QuantityMeasurementApp.LengthUnit.FEET,
	                        QuantityMeasurementApp.LengthUnit.INCH);

	        assertEquals(12.0, result, EPSILON);
	    }

	    @Test
	    public void testConversion_YardToFeet() {

	        double result =
	                QuantityMeasurementApp.QuantityLength.convert(
	                        3.0,
	                        QuantityMeasurementApp.LengthUnit.YARDS,
	                        QuantityMeasurementApp.LengthUnit.FEET);

	        assertEquals(9.0, result, EPSILON);
	    }

	    @Test
	    public void testConversion_CentimeterToInch() {

	        double result =
	                QuantityMeasurementApp.QuantityLength.convert(
	                        2.54,
	                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
	                        QuantityMeasurementApp.LengthUnit.INCH);

	        assertEquals(1.0, result, 1e-3);
	    }

	    @Test
	    public void testRoundTripConversion() {

	        double original = 5.0;

	        double inches =
	                QuantityMeasurementApp.QuantityLength.convert(
	                        original,
	                        QuantityMeasurementApp.LengthUnit.FEET,
	                        QuantityMeasurementApp.LengthUnit.INCH);

	        double backToFeet =
	                QuantityMeasurementApp.QuantityLength.convert(
	                        inches,
	                        QuantityMeasurementApp.LengthUnit.INCH,
	                        QuantityMeasurementApp.LengthUnit.FEET);

	        assertEquals(original, backToFeet, EPSILON);
	    }


	    @Test
	    public void testInvalidSourceUnit() {

	        assertThrows(IllegalArgumentException.class, () -> {
	            QuantityMeasurementApp.QuantityLength.convert(
	                    1.0,
	                    null,
	                    QuantityMeasurementApp.LengthUnit.FEET);
	        });
	    }

	    @Test
	    public void testInvalidTargetUnit() {

	        assertThrows(IllegalArgumentException.class, () -> {
	            QuantityMeasurementApp.QuantityLength.convert(
	                    1.0,
	                    QuantityMeasurementApp.LengthUnit.FEET,
	                    null);
	        });
	    }

	    @Test
	    public void testNaNValue() {

	        assertThrows(IllegalArgumentException.class, () -> {
	            QuantityMeasurementApp.QuantityLength.convert(
	                    Double.NaN,
	                    QuantityMeasurementApp.LengthUnit.FEET,
	                    QuantityMeasurementApp.LengthUnit.INCH);
	        });
	    }
}
