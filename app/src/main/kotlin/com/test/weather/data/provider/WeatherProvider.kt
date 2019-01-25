package com.test.weather.data.provider

import androidx.lifecycle.LiveData
import com.test.weather.data.db.entity.WeatherEntity
import retrofit2.http.Query

interface WeatherProvider {
    fun getWeatherByCoordinate(@Query("lat") lat: Double,
                               @Query("lon") lng: Double): LiveData<WeatherEntity>

    fun getWeatherByName(@Query("q") cityName: String): LiveData<WeatherEntity>

    fun getWeatherById(@Query("id") cityCode: Int): LiveData<WeatherEntity>

    fun getLocalWeatherByCoordinate(@Query("lat") lat: Double,
                               @Query("lon") lng: Double): LiveData<WeatherEntity>

    fun getLocalWeatherByName(@Query("q") cityName: String): LiveData<WeatherEntity>

    fun getLocalWeatherById(@Query("id") cityCode: Int): LiveData<WeatherEntity>

    fun deleteWeather(id: Int)

    fun saveWeather(weatherEntity: WeatherEntity)
}