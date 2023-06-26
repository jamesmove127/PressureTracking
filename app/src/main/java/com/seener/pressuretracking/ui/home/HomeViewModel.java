package com.seener.pressuretracking.ui.home;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mTextAction;
    private final MutableLiveData<String> mTextPressure;
    private final MutableLiveData<String> mTextAltitude;
    private final MutableLiveData<String> mTextTemperature;

    public HomeViewModel() {
        mTextAction = new MutableLiveData<>();
        mTextPressure = new MutableLiveData<>();
        mTextAltitude = new MutableLiveData<>();
        mTextTemperature = new MutableLiveData<>();
        mTextAction.setValue("IDLE");
        mTextPressure.setValue("999");
        mTextAltitude.setValue("0");
        mTextTemperature.setValue("15C");
    }

    public LiveData<String> getAction() {
        return mTextAction;
    }

    public LiveData<String> getPressure() {
        return mTextPressure;
    }

    public LiveData<String> getAltitude() {
        return mTextAltitude;
    }

    public void setTextAction(String value) {
        mTextAction.setValue(value);
    }


    public void setTextPressure(String value) {
        mTextPressure.setValue(value);
    }

    public void setTextAltitude(String value) {
        mTextAltitude.setValue(value);
    }

    public LiveData<String> getTemperature() {
        return mTextTemperature;
    }

    public void setTextTemperature(String value) {
        mTextTemperature.setValue(value);
    }
}