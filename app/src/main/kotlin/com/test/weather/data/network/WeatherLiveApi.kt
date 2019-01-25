package com.test.weather.data.network

import com.test.weather.data.db.entity.WeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherLiveApi {
    @GET("data/2.5/weather")
    fun getWeatherByLatLon(@Query("lat") lat: Double,
                           @Query("lon") lng: Double,
                           @Query("APPID") appId: String,
                           @Query("lang") lang: String,
                           @Query("units") units:String): Call<WeatherEntity>

    @GET("data/2.5/weather")
    fun getWeatherByName(@Query("q") cityName: String,
                         @Query("APPID") appId: String,
                         @Query("lang") lang: String,
                         @Query("units") units:String): Call<WeatherEntity>

    @GET("data/2.5/weather")
    fun getWeatherByCode(@Query("id") cityCode: Int,
                         @Query("APPID") appId: String,
                         @Query("lang") lang: String,
                         @Query("units") units:String): Call<WeatherEntity>
}