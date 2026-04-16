package com.quantitymeasurement.quantity_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quantitymeasurement.quantity_service.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Sabki history (old - fallback ke liye)
    List<QuantityMeasurementEntity> findByOperation(String operation);

    // Specific user ki history
    List<QuantityMeasurementEntity> findByUserIdAndOperation(Long userId, String operation);

    // Specific user ka count
    long countByUserIdAndOperationAndErrorFalse(Long userId, String operation);

    // Purane methods (fallback)
    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);
    long countByOperationAndErrorFalse(String operation);
    List<QuantityMeasurementEntity> findByErrorTrue();
}