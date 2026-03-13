package com.quantity;

public interface IMeasurable {
	double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    default boolean supportsArithmetic() {
        SupportsArithmetic supportsArithmetic = () -> true;
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        
    }
} 