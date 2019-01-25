package com.test.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.weather.data.db.entity.WeatherEntity

@Dao
interface WeatherDao {

    @get:Query("SELECT * FROM city_weather")
    val all: LiveData<List<WeatherEntity>>

    @Query("SELECT * FROM city_weather WHERE id = :id")
    fun findBy(vararg id: Int): LiveData<WeatherEntity>

    @Query("SELECT * FROM city_weather WHERE name = :name")
    fun findBy(vararg name: String): LiveData<WeatherEntity>

    @Query("SELECT * FROM city_weather WHERE coordlat = :lat AND coordlon = :lon")
    fun findBy(lat: Double, lon: Double): LiveData<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg weatherEntity: WeatherEntity)

    @Delete
    fun delete(vararg weatherEntity: WeatherEntity)

    @Query("DELETE FROM city_weather WHERE id = :id")
    fun deleteById(vararg id: Int?)

    @Query("DELETE FROM city_weather")
    fun clearData(): Int
}