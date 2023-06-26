package com.seener.pressuretracking.modle;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("current")
    private TemperatureData temperatureData;

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }
}
