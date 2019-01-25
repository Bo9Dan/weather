package com.test.weather.data.provider

interface StateProvider {
    fun isAirplaneModeOn(): Boolean
    fun isGPSOn(): Boolean
    fun isNetworkOn(): Boolean
}