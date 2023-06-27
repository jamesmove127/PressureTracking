package com.seener.pressuretracking.http;

import com.seener.pressuretracking.model.WeatherData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFetcher {

    //https://api.weatherapi.com/v1/current.json?key=a66724f71e1d4df198b113109232606&q=Vancouver&aqi=no
    private static final String BASE_URL = "https://api.weatherapi.com/v1/";
    private static final String API_KEY = "a66724f71e1d4df198b113109232606";

    private WeatherService weatherService;

    private static volatile WeatherFetcher INSTANCE = null;

    public static WeatherFetcher getInstance() {
        if (INSTANCE == null) {
            synchronized (WeatherFetcher.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WeatherFetcher();
                }
            }
        }
        return INSTANCE;
    }

    private WeatherFetcher() {


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    public Disposable fetchWeatherData(String cityName, Consumer<WeatherData> onWeatherDataReceived, Consumer<Throwable> onWeatherDataError) {
        return weatherService.getWeatherData(API_KEY, cityName, "no")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onWeatherDataReceived, onWeatherDataError);
    }

    public void fetchWeatherData(double latitude, double longitude, Consumer<WeatherData> onWeatherDataReceived, Consumer<Throwable> onWeatherDataError) {
        weatherService.getWeatherData(latitude, longitude, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onWeatherDataReceived, onWeatherDataError);
    }
}
