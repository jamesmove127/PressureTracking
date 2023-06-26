package com.seener.pressuretracking.ui.home;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.seener.pressuretracking.databinding.FragmentHomeBinding;
import com.seener.pressuretracking.modle.Pressure;

public class HomeFragment extends Fragment implements SensorEventListener {

    private FragmentHomeBinding binding;
    private HomeViewModel mhHomeViewModel;

    private SensorManager mSensorManager;
    private Sensor mPressure;
    private Context mContext;


    private TextView textAction;
    private TextView textPessure;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mhHomeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mContext = getActivity().getApplicationContext();

        initSensor();

        textAction = binding.textAction;
        textPessure = binding.textPressure;

        mhHomeViewModel.getAction().observe(getViewLifecycleOwner(), textAction::setText);
        mhHomeViewModel.getPressure().observe(getViewLifecycleOwner(), textPessure::setText);

        return root;
    }

    private void initSensor() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure != null) {
            mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_UI);
            Toast.makeText(mContext, "Success! There's a barometer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Failed! There isn't a barometer", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        Pressure pressure = new Pressure();
        pressure.setPressure(values[0]);
        mhHomeViewModel.setTextPressure(String.valueOf(pressure.getPressure()));
        mhHomeViewModel.setTextAction(String.valueOf(pressure.getAction()));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}