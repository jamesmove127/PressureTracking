package com.seener.pressuretracking.ui.home;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel{

    private final MutableLiveData<String> mTextAction;
    private final MutableLiveData<String> mTextPressure;

    public HomeViewModel() {
        mTextAction = new MutableLiveData<>();
        mTextPressure = new MutableLiveData<>();
        mTextAction.setValue("IDLE");
        mTextPressure.setValue("999");
    }

    public LiveData<String> getAction() {
        return mTextAction;
    }

    public LiveData<String> getPressure() {
        return mTextPressure;
    }

    public void setTextAction(String value){
        mTextAction.setValue(value);
    }


    public void setTextPressure(String value){
        mTextPressure.setValue(value);
    }

}