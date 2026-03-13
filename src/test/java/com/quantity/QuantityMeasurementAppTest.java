package com.quantity;

import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testService_Add() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 =
                new QuantityDTO(10, "FEET", "LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(5, "FEET", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(15, result.getValue());
    }

    @Test
    public void testService_Subtract() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 =
                new QuantityDTO(10, "FEET", "LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(5, "FEET", "LENGTH");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(5, result.getValue());
    }

    @Test
    public void testService_Divide() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 =
                new QuantityDTO(10, "FEET", "LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(2, "FEET", "LENGTH");

        assertEquals(5, service.divide(q1, q2));
    }

    @Test
    public void testService_DivideByZero() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO q1 =
                new QuantityDTO(10, "FEET", "LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(0, "FEET", "LENGTH");

        assertThrows(ArithmeticException.class,
                () -> service.divide(q1, q2));
    }
}
