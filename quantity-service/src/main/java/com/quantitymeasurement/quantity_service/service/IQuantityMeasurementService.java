package com.quantitymeasurement.quantity_service.service;

import com.quantitymeasurement.quantity_service.dto.QuantityInputDTO;
import com.quantitymeasurement.quantity_service.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(QuantityInputDTO input);
    QuantityMeasurementEntity convert(QuantityInputDTO input);
    QuantityMeasurementEntity add(QuantityInputDTO input);
    QuantityMeasurementEntity subtract(QuantityInputDTO input);
    QuantityMeasurementEntity multiply(QuantityInputDTO input);
    QuantityMeasurementEntity divide(QuantityInputDTO input);

    // userId ke saath history/count
    List<QuantityMeasurementEntity> getHistoryByOperation(String operation, Long userId);
    long getOperationCount(String operation, Long userId);
}