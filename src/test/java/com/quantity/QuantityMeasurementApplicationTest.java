package com.quantity;

import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementApplicationTest {

    // ==============================
    // UNIT TESTS
    // ==============================

    @Test
    void givenSameFeet_shouldReturnEqual() {

        Quantity q1 = new Quantity(5, LengthUnit.FEET);
        Quantity q2 = new Quantity(5, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void givenFeetAndInch_shouldReturnEqual() {

        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inch = new Quantity(12, LengthUnit.INCHES);

        assertTrue(feet.equals(inch));
    }

    @Test
    void givenDifferentValues_shouldReturnNotEqual() {

        Quantity q1 = new Quantity(5, LengthUnit.FEET);
        Quantity q2 = new Quantity(6, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void givenFeetAndFeet_whenAdded_shouldReturnSum() {

        Quantity q1 = new Quantity(2, LengthUnit.FEET);
        Quantity q2 = new Quantity(3, LengthUnit.FEET);

        Quantity result = q1.add(q2);

        assertEquals(5, result.getValue(), 0.0);
    }

    @Test
    void givenFeetAndInch_whenAdded_shouldConvertCorrectly() {

        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inch = new Quantity(12, LengthUnit.INCHES);

        Quantity result = feet.add(inch);

        assertEquals(2, result.getValue(), 0.0);
    }

    // ==============================
    // EXCEPTION TESTS
    // ==============================

    @Test
    void givenNullOperand_shouldThrowException() {

        Quantity q1 = new Quantity(5, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            q1.add(null);
        });
    }

    @Test
    void givenZeroDivisor_shouldThrowException() {

        Quantity q1 = new Quantity(10, LengthUnit.FEET);
        Quantity q2 = new Quantity(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> {
            q1.divide(q2);
        });
    }

    // ==============================
    // SERVICE TESTS
    // ==============================

    @Test
    void serviceShouldAddQuantities() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 = new QuantityDTO(3, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "FEET", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(5, result.getValue());
    }

    @Test
    void serviceShouldSubtractQuantities() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 = new QuantityDTO(8, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(3, "FEET", "LENGTH");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(5, result.getValue());
    }

    // ==============================
    // INTEGRATION TEST
    // ==============================

    @Test
    void integrationTest_ServiceWithRepository() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 = new QuantityDTO(5, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(7, "FEET", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(12, result.getValue());
    }
}
