package com.test.weather.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_name")
data class CityEntity(
        @field:PrimaryKey
        @ColumnInfo(name = "id")
        val geonameId: Int,
        val name: String,
        val lng: Double,
        val lat: Double,
        val countryId: String,
        val countryName: String,
        val countryCode: String,
        val population: Int
)
