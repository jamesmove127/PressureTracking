package com.seener.pressuretracking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DownUpStrategy implements IStrategy {

    private final static float THETA = 0.01f;
    private final static int N0 = 5;
    private final static int N1 = 5;

    private final List<Float> pressureList = Collections.synchronizedList(new ArrayList<Float>());

    @Override
    public synchronized Pressure.Action execute(float value) {
        pressureList.add(value);

        int size = pressureList.size();

        if (size > 1000) {
            for (int i = 0; i < size - 1000; i++) {
                pressureList.remove(0);
            }
        }

        float startValue = 0f;
        float endValue = 0f;

        if (size < N0) {
            return Pressure.Action.IDLE;
        }

        boolean fragmentDetected = false;
        for (int i = 0; i <= pressureList.size() - N0; i++) {
            boolean allChangesExceedThreshold = true;
            for (int j = i + 1; j < i + N0; j++) {
                float currentValue = pressureList.get(j - 1);
                float nextValue = pressureList.get(j);
                float difference = Math.abs(nextValue - currentValue);

                if (difference <= THETA) {
                    allChangesExceedThreshold = false;
                    break;
                }
            }

            if (allChangesExceedThreshold) {
                fragmentDetected = true;
                startValue = pressureList.get(i);
                break;
            }
        }

        if (fragmentDetected) {
            for (int i = pressureList.size() - 1; i >= 0; i--) {
                float currentValue = pressureList.get(i);
                float nextValue = pressureList.get(i - 1);
                float difference = Math.abs(nextValue - currentValue);

                if (difference <= THETA) {
                    if (pressureList.size() - i >= N1) {
                        endValue = currentValue;
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        if (startValue != 0f && endValue != 0) {
            pressureList.clear();
            return startValue > endValue ? Pressure.Action.UP_STAIRS : Pressure.Action.DOWN_STAIRS;
        }

        return Pressure.Action.IDLE;
    }


}
