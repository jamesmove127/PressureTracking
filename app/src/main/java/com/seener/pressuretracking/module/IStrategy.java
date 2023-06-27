package com.seener.pressuretracking.module;

public interface IStrategy {
    Pressure.Action execute(float value);
}
