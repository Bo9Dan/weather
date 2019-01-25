package com.test.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.weather.data.db.entity.CityEntity

@Dao
interface CityDao {

    @get:Query("SELECT * FROM city_name")
    val allLive: LiveData<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(name: CityEntity)

    @Delete
    fun deleteCity(cityEntity: CityEntity): Int

    @Query("DELETE FROM city_name WHERE id = :id")
    fun deleteById(id: Int): Int

    @Query("DELETE FROM city_name WHERE name = :name AND countryCode= :code")
    fun deleteCityByNameAndCode(name: String, code: String): Int

    @Delete()
    fun delete(vararg cityEntities: CityEntity): Int

    @Query("DELETE FROM city_name")
    fun clearData(): Int
}