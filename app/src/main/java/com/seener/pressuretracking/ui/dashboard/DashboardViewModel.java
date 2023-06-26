package com.seener.pressuretracking.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.data.LineData;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<LineData> mValue;

    public DashboardViewModel() {
        mValue = new MutableLiveData<>();
        LineData data = new LineData();
        mValue.setValue(data);
    }

    public LiveData<LineData> getValue() {
        return mValue;
    }

    public void setValue(LineData data){
        mValue.setValue(data);
    }
}