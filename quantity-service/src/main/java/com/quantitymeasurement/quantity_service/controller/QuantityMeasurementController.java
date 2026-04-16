package com.quantitymeasurement.quantity_service.controller;

import com.quantitymeasurement.quantity_service.dto.*;
import com.quantitymeasurement.quantity_service.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.quantity_service.model.*;
import com.quantitymeasurement.quantity_service.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // Helper: Gateway se aane wala X-User-Id header input mein inject karo
    private void injectUserId(QuantityInputDTO input, String userIdHeader) {
        if (userIdHeader != null && !userIdHeader.isBlank()) {
            try {
                input.setUserId(Long.parseLong(userIdHeader));
            } catch (NumberFormatException e) {
                // invalid header → userId null rahega
            }
        }
    }

    // COMPARE
    @PostMapping("/compare")
    public QuantityMeasurementEntity compare(
            @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        injectUserId(input, userIdHeader);
        return service.compare(input);
    }

    // CONVERT
    @PostMapping("/convert")
    public QuantityMeasurementEntity convert(
            @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        injectUserId(input, userIdHeader);
        return service.convert(input);
    }

    // ADD
    @PostMapping("/add")
    public QuantityMeasurementEntity add(
            @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        injectUserId(input, userIdHeader);
        return service.add(input);
    }

    // SUBTRACT
    @PostMapping("/subtract")
    public QuantityMeasurementEntity subtract(
            @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        injectUserId(input, userIdHeader);
        return service.subtract(input);
    }

    // MULTIPLY
    @PostMapping("/multiply")
    public QuantityMeasurementEntity multiply(
            @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        injectUserId(input, userIdHeader);
        return service.multiply(input);
    }

    // DIVIDE
    @PostMapping("/divide")
    public QuantityMeasurementEntity divide(
            @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        injectUserId(input, userIdHeader);
        return service.divide(input);
    }

    // HISTORY - sirf is user ki
    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementEntity> getHistory(
            @PathVariable String operation,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        Long userId = null;
        if (userIdHeader != null && !userIdHeader.isBlank()) {
            try { userId = Long.parseLong(userIdHeader); } catch (NumberFormatException ignored) {}
        }
        return service.getHistoryByOperation(operation, userId);
    }

    // COUNT - sirf is user ka
    @GetMapping("/count/{operation}")
    public long getCount(
            @PathVariable String operation,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader) {
        Long userId = null;
        if (userIdHeader != null && !userIdHeader.isBlank()) {
            try { userId = Long.parseLong(userIdHeader); } catch (NumberFormatException ignored) {}
        }
        return service.getOperationCount(operation, userId);
    }

    @GetMapping("/test")
    public String testApi() {
        return "This is secured API";
    }
}