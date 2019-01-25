package com.test.weather.injection.module

import com.test.weather.data.network.CityApi
import com.test.weather.data.network.WeatherLiveApi
import com.test.weather.internal.CITIES_BASE_URL
import com.test.weather.internal.OPEN_WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
class NetworkModule {

    @Provides
    @Reusable
    internal fun provideCitiesApi(): CityApi {
        return Retrofit.Builder()
                .baseUrl(CITIES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build().create(CityApi::class.java)
    }

    @Provides
    @Reusable
    internal fun provideOpenWeatherApi(): WeatherLiveApi {
        return Retrofit.Builder()
                .baseUrl(OPEN_WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build().create(WeatherLiveApi::class.java)
    }
}