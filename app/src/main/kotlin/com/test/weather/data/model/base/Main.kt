package com.test.weather.data.model.base

data class Main(
        val humidity: Int,
        val pressure: Double = 0.toDouble(),
        val temp: Double = 0.toDouble(),
        val temp_min: Double = 0.toDouble(),
        val temp_max: Double = 0.toDouble()
)