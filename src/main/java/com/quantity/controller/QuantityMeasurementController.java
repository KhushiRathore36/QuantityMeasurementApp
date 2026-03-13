package com.quantity.controller;

import com.quantity.dto.QuantityDTO;
import com.quantity.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) {

        QuantityDTO result = service.add(q1, q2);

        System.out.println("Addition Result: " + result.getValue());
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {

        boolean result = service.compare(q1, q2);

        System.out.println("Comparison Result: " + result);
    }
}
