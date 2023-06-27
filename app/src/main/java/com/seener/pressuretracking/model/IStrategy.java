package com.seener.pressuretracking.model;

public interface IStrategy {
    Pressure.Action execute(float value);
}
