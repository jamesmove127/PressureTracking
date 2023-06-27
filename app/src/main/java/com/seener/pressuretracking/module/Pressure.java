package com.seener.pressuretracking.module;

import com.seener.pressuretracking.utils.PressureToActionManager;

public class Pressure {

    private float pressure;
    private Action action;
    private IStrategy IStrategy;


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

    public IStrategy getStrategy() {
        return IStrategy;
    }

    public void setStrategy(IStrategy IStrategy) {
        this.IStrategy = IStrategy;
    }

    public enum Action {
        IDLE,
        WALKING,
        RUNNING,
        DRIVING,
        UP_STAIRS,
        DOWN_STAIRS,
    }
}
