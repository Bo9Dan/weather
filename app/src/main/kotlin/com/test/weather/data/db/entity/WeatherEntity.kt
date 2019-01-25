package com.test.weather.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.weather.data.model.base.Coord
import com.test.weather.data.model.base.Main
import com.test.weather.data.model.base.Weather
import com.test.weather.data.model.base.Wind

@Entity(tableName = "city_weather")
data class WeatherEntity(
        @field:PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        val name: String,
        val visibility: Int,
        @Embedded(prefix = "coord")
        val coord: Coord,
        @Embedded(prefix = "weather")
        val weather: ArrayList<Weather>,
        @Embedded(prefix = "main")
        val main: Main,
        @Embedded(prefix = "wind")
        val wind: Wind,
        val dt: Long
)