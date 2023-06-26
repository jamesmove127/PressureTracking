package com.seener.pressuretracking.modle;

public interface IStrategy {
    Pressure.Action execute(float value);
}
