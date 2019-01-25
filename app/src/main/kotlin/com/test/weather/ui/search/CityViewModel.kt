package com.test.weather.ui.search

import androidx.lifecycle.MutableLiveData
import com.test.weather.ui.base.BaseViewModel
import com.test.weather.data.db.entity.CityEntity

class CityViewModel : BaseViewModel() {

    private val cityName = MutableLiveData<String>()
    private val country = MutableLiveData<String>()
    private val population = MutableLiveData<Int>()

    fun bind(geoName: CityEntity) {
        this.cityName.value = geoName.name
        this.country.value = geoName.countryName
        this.population.value = geoName.population
    }

    fun getCityName(): MutableLiveData<String> {
        return cityName
    }

    fun getCountry(): MutableLiveData<String> {
        return country
    }

    fun getPopulation(): MutableLiveData<Int> {
        return population
    }

}