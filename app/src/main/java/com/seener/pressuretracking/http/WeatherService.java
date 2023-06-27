package com.seener.pressuretracking.http;

import com.seener.pressuretracking.module.WeatherData;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("current.json")
    Observable<WeatherData> getWeatherData(
            @Query("key") String apiKey,
            @Query("q") String cityName,
            @Query("api") String api
    );


    @GET("onecall")
    Single<WeatherData> getWeatherData(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("appid") String apiKey);

}
