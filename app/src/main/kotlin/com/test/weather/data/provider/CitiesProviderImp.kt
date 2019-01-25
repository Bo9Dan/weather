package com.test.weather.data.provider

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.weather.data.db.AppDatabase
import com.test.weather.data.db.CityDao
import com.test.weather.data.db.entity.CityEntity
import com.test.weather.data.model.response.CitiesListResponse
import com.test.weather.data.network.CityApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class CitiesProviderImp @Inject constructor(
        private val dataBase: AppDatabase,
        private val apiService: CityApi
) : CitiesProvider {

    private var allGeoNames: LiveData<List<CityEntity>>

    lateinit var cityDao :CityDao

    init {
        cityDao = dataBase.cityDao()
        allGeoNames = dataBase.cityDao().allLive
    }

    override fun searchCityByName(name: String): LiveData<List<CityEntity>> {
        val data = MutableLiveData<List<CityEntity>>()
        apiService.getCitiesByName(name,
                Locale.getDefault().country, 12,
                "LONG",
                "crazyweather",
                "json",
                0.8,
                "P",
                "relevance",
                true)
                .enqueue(object : Callback<CitiesListResponse> {
                    override fun onFailure(call: Call<CitiesListResponse>, t: Throwable) =
                            data.setValue(null)

                    override fun onResponse(call: Call<CitiesListResponse>, response: Response<CitiesListResponse>) {
                        if (response.isSuccessful) {
                            data.setValue(response.body()!!.geonames)
                        }
                    }

                })
        return data
    }

    override fun retrieveAllSavedPlaces(): LiveData<List<CityEntity>> {
        return allGeoNames
    }

    override fun insertCity(city: CityEntity) {
        InsertAsyncTaskTask(cityDao).execute(city)
    }

    override fun deleteCity(city: CityEntity) {
        DeleteAsyncTaskTask(cityDao).execute(city)
    }

    private class InsertAsyncTaskTask internal constructor(private val mAsyncTaskDao: CityDao) : AsyncTask<CityEntity, Void, Void>() {

        override fun doInBackground(vararg params: CityEntity): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTaskTask internal constructor(private val mAsyncTaskDao: CityDao) : AsyncTask<CityEntity, Void, Void>() {

        override fun doInBackground(vararg params: CityEntity): Void? {
            mAsyncTaskDao.delete(params[0])
            return null
        }
    }
}