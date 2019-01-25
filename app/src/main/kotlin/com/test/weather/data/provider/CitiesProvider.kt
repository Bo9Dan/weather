package com.test.weather.data.provider

import androidx.lifecycle.LiveData
import com.test.weather.data.db.entity.CityEntity

interface CitiesProvider {
    fun searchCityByName(name: String): LiveData<List<CityEntity>>

    fun retrieveAllSavedPlaces(): LiveData<List<CityEntity>>

    fun insertCity(city: CityEntity)

    fun deleteCity(city: CityEntity)
}