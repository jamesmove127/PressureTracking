package com.seener.pressuretracking.utils;

import com.seener.pressuretracking.modle.Pressure;

public class PressureToActionManager {

    private volatile static PressureToActionManager INSTANCE = null;

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

    public Pressure.Action pressureToAction(float pressure){
        //TODO
        return Pressure.Action.IDLE;
    }
}
