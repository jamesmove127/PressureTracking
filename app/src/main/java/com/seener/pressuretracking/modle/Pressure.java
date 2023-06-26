package com.seener.pressuretracking.modle;

import com.seener.pressuretracking.utils.PressureToActionManager;

public class Pressure {

    private float pressure;
    private Action action;
    private Strategy strategy;


    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public Action getAction() {
        return PressureToActionManager.getInstance().pressureToAction(getPressure());
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public enum Action {
        IDLE,
        WALKING,
        RUNNING,
        DRIVING,
    }
}
