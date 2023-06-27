package com.seener.pressuretracking.module;

import com.google.gson.annotations.SerializedName;

public class TemperatureData {
    @SerializedName("temp_c")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }
}
