package com.test.weather.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    /*private val injector: AppComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    *//**
     * Injects the required dependencies
     *//*
    private fun inject() {
        when (this) {
            //Activities ViewModel
            is CitySearchActivityViewModel -> injector.inject(this)
            is MainActivityViewModel -> injector.inject(this)
            is DetailActivityViewModel -> injector.inject(this)

            //Else viewModels for adapters and others
            is CityViewModel -> injector.inject(this)
//            is MainViewModel -> injector.inject(this)
//            is WeatherDetailViewModel -> injector.inject(this)
        }
    }*/
}