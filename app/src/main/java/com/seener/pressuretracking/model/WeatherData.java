package com.seener.pressuretracking.model;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("current")
    private TemperatureData temperatureData;

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }
}
