package com.quantity.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operation;
    private String result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation, String result) {
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public boolean hasError() {
        return error;
    }

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}