package com.test.weather.injection.module

import com.test.weather.data.db.AppDatabase
import com.test.weather.data.network.CityApi
import com.test.weather.data.network.WeatherLiveApi
import com.test.weather.data.provider.CitiesProvider
import com.test.weather.data.provider.CitiesProviderImp
import com.test.weather.data.provider.WeatherProvider
import com.test.weather.data.provider.WeatherProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProviderModule {

    @Provides
    @Singleton
    internal fun bindCityProvider(db: AppDatabase, api: CityApi): CitiesProvider = CitiesProviderImp(db, api)

    @Provides
    @Singleton
    internal fun bindWeather(db: AppDatabase, api: WeatherLiveApi): WeatherProvider = WeatherProviderImpl(db, api)

}