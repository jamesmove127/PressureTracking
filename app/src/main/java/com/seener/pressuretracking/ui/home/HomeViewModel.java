package com.seener.pressuretracking.ui.home;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.seener.pressuretracking.http.WeatherFetcher;
import com.seener.pressuretracking.modle.DownUpStrategy;
import com.seener.pressuretracking.modle.IStrategy;
import com.seener.pressuretracking.modle.Pressure;
import com.seener.pressuretracking.modle.StrategyContext;
import com.seener.pressuretracking.modle.TemperatureData;
import com.seener.pressuretracking.modle.WeatherData;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mTextAction;
    private final MutableLiveData<String> mTextPressure;
    private final MutableLiveData<String> mTextAltitude;
    private final MutableLiveData<String> mTextTemperature;

    private long pressureCount = 0;

    private IStrategy downUpStrategy;
    private StrategyContext strategyContext;

    public HomeViewModel() {
        mTextAction = new MutableLiveData<>();
        mTextPressure = new MutableLiveData<>();
        mTextAltitude = new MutableLiveData<>();
        mTextTemperature = new MutableLiveData<>();
        mTextAction.setValue("IDLE");
        mTextPressure.setValue("999");
        mTextAltitude.setValue("0");
        mTextTemperature.setValue("15C");

        downUpStrategy = new DownUpStrategy();
        strategyContext = new StrategyContext(downUpStrategy);
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


    public void setTextPressure(float value) {
        mTextPressure.setValue("Pressure\n" + value + "\nhPa");

        if (pressureCount % 10 == 0) {
            Pressure.Action action = strategyContext.executeStrategy(value);
            mTextAction.setValue("Action\n" + action);
        }
        if (pressureCount > Integer.MAX_VALUE / 2) {
            pressureCount = 0;
        } else {
            pressureCount++;
        }
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

    public Disposable getTemperatureFromWeb(String cityName) {
        return WeatherFetcher.getInstance().fetchWeatherData(cityName, new Consumer<WeatherData>() {
            @Override
            public void accept(WeatherData weatherData) throws Throwable {
                TemperatureData temperatureData = weatherData.getTemperatureData();
                double temperature = temperatureData.getTemperature();
                Log.i("WeatherFetcher", "Current temperature: " + temperature);
                setTextTemperature("Temperature\n" + temperature + " C");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.e("WeatherFetcher", "Error fetching weather data: " + throwable.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("FA", "HomeViewModel onCleared");

    }
}