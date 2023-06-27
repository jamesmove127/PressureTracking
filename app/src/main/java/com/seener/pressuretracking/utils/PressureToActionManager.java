package com.seener.pressuretracking.utils;

import com.seener.pressuretracking.module.Pressure;

public class PressureToActionManager {

    private volatile static PressureToActionManager INSTANCE = null;

    private static final double GAS_CONSTANT = 8.314; // J/(mol·K)
    private static final double GRAVITY = 9.80665; // m/s²
    private static final double MOLAR_MASS = 0.02896; // kg/mol
    private static final double REFERENCE_PRESSURE = 101325; // Pa

    private PressureToActionManager() {
    }

    public static PressureToActionManager getInstance() {
        if (INSTANCE == null) {
            synchronized (PressureToActionManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PressureToActionManager();
                }
            }
        }
        return INSTANCE;
    }

    public Pressure.Action pressureToAction(double pressure) {
        //TODO
        return Pressure.Action.IDLE;
    }

    public double getAltitude(double pressure, double temperatureInCelsius) {
        double temperature = getTemperature(temperatureInCelsius); // 获取温度（单位：开尔文）
        double altitude = -((GAS_CONSTANT * temperature) / (GRAVITY * MOLAR_MASS))
                * Math.log(pressure / REFERENCE_PRESSURE);
        return altitude;
    }

    private double getTemperature(double temperatureInCelsius) {
        double temperatureInKelvin = temperatureInCelsius + 273.15; // 转换为开尔文温标
        return temperatureInKelvin;
    }
}
