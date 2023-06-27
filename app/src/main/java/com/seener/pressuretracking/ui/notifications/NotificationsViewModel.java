package com.seener.pressuretracking.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<List<String>> stringList;

    public NotificationsViewModel() {
        stringList = new MutableLiveData<>();
    }

    public void setList(List<String> strings){
        stringList.setValue(strings);

    }

    public LiveData<List<String>> getList() {
        return stringList;
    }
}