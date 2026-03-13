package com.quantity;

public interface IMeasurable {

    double toBaseUnit(double value);

    double fromBaseUnit(double value);
}