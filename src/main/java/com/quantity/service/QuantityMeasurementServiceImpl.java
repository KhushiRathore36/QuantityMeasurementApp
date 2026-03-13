package com.quantity.service;

import com.quantity.dto.QuantityDTO;
import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        repository.save(
                new QuantityMeasurementEntity("CONVERT",
                        quantity.getValue() + " " + quantity.getUnit())
        );

        return new QuantityDTO(quantity.getValue(), targetUnit,
                quantity.getMeasurement());
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        boolean result = q1.getValue() == q2.getValue();

        repository.save(
                new QuantityMeasurementEntity("COMPARE", String.valueOf(result))
        );

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() + q2.getValue();

        repository.save(
                new QuantityMeasurementEntity("ADD", String.valueOf(result))
        );

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurement());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() - q2.getValue();

        repository.save(
                new QuantityMeasurementEntity("SUBTRACT", String.valueOf(result))
        );

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurement());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        if (q2.getValue() == 0)
            throw new ArithmeticException("Division by zero");

        double result = q1.getValue() / q2.getValue();

        repository.save(
                new QuantityMeasurementEntity("DIVIDE", String.valueOf(result))
        );

        return result;
    }
}
