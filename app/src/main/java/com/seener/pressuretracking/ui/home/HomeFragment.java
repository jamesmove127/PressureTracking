package com.seener.pressuretracking.ui.home;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.seener.pressuretracking.databinding.FragmentHomeBinding;
import com.seener.pressuretracking.location.LocationFetcher;
import com.seener.pressuretracking.model.Pressure;

import io.reactivex.rxjava3.disposables.Disposable;

public class HomeFragment extends Fragment implements SensorEventListener {

    private FragmentHomeBinding binding;
    private HomeViewModel mHomeViewModel;

    private SensorManager mSensorManager;
    private Sensor mPressure;
    private Context mContext;

    private LocationFetcher locationFetcher;

    private Disposable getTemperatureFromWebDisposable;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHomeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mContext = getActivity().getApplicationContext();

        initSensor();
//        initTemperature(49.2827, -123.1207);


        TextView textAction = binding.textAction;
        TextView textPressure = binding.textPressure;
        TextView textAltitude = binding.textAltitude;
        TextView textTemperature = binding.textTemperature;

        mHomeViewModel.getAction().observe(getViewLifecycleOwner(), textAction::setText);
        mHomeViewModel.getPressure().observe(getViewLifecycleOwner(), textPressure::setText);
        mHomeViewModel.getAltitude().observe(getViewLifecycleOwner(), textAltitude::setText);
        mHomeViewModel.getTemperature().observe(getViewLifecycleOwner(), textTemperature::setText);

        initTemperature("Vancouver");
        initLocation();

        return root;
    }

    private void initSensor() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure != null) {
            mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(mContext, "Failed! There isn't a barometer", Toast.LENGTH_LONG).show();
        }
    }

    private void initLocation() {
        locationFetcher = new LocationFetcher(mContext);
        locationFetcher.startLocationUpdates(mContext, new LocationFetcher.LocationCallback() {
            @Override
            public void onLocationReceived(double latitude, double longitude) {
//                initTemperature(latitude, longitude);
            }
        });

    }

    private void initTemperature(String cityName) {
        if (getTemperatureFromWebDisposable != null && !getTemperatureFromWebDisposable.isDisposed()) {
            getTemperatureFromWebDisposable.dispose();
        }
        getTemperatureFromWebDisposable = mHomeViewModel.getTemperatureFromWeb(cityName);
    }

    @Override
    public void onDestroyView() {
        mSensorManager.unregisterListener(this);
        locationFetcher.stopLocationUpdates();
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        Pressure pressure = new Pressure();
        pressure.setPressure(values[0]);
        mHomeViewModel.setTextPressure(pressure.getPressure());

        float altitude = SensorManager.getAltitude(1013.25f, pressure.getPressure()); // 计算海拔高度
        mHomeViewModel.setTextAltitude("Altitude\n" + altitude + "\nm");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        if (getTemperatureFromWebDisposable != null && !getTemperatureFromWebDisposable.isDisposed()) {
            getTemperatureFromWebDisposable.dispose();
        }
        super.onDestroy();
    }
}