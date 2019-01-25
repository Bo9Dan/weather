package com.test.weather.data.provider

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.weather.data.db.AppDatabase
import com.test.weather.data.db.WeatherDao
import com.test.weather.data.db.entity.WeatherEntity
import com.test.weather.data.network.WeatherLiveApi
import com.test.weather.internal.METRICK_UNIT_SYSTEM
import com.test.weather.internal.OPEN_WEATHER_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class WeatherProviderImpl @Inject constructor(
        private val dataBase: AppDatabase,
        private val apiService: WeatherLiveApi
) : WeatherProvider {

    private val language: String = Locale.getDefault().country
    lateinit var weatherDao: WeatherDao

    init {
        weatherDao = dataBase.weatherDao()
    }


    override fun getWeatherByCoordinate(lat: Double, lng: Double): LiveData<WeatherEntity> {
        val data = MutableLiveData<WeatherEntity>()
        apiService.getWeatherByLatLon(lat, lng, OPEN_WEATHER_KEY, language, METRICK_UNIT_SYSTEM)
                .enqueue(object : Callback<WeatherEntity> {
                    override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                        data.value = null
                    }

                    override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                        if (response.isSuccessful) {
                            val value = response.body()
                            data.value = value
                            saveWeather(value!!)
                        }
                    }

                })
        return data
    }

    override fun getWeatherByName(cityName: String): LiveData<WeatherEntity> {
        val data = MutableLiveData<WeatherEntity>()
        apiService.getWeatherByName(cityName, OPEN_WEATHER_KEY, language, METRICK_UNIT_SYSTEM)
                .enqueue(object : Callback<WeatherEntity> {
                    override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                        data.value = null
                    }

                    override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                        if (response.isSuccessful) {
                            val value = response.body()
                            data.value = value
                            saveWeather(value!!)
                        }
                    }

                })
        return data
    }

    override fun getWeatherById(cityCode: Int): LiveData<WeatherEntity> {
        val data = MutableLiveData<WeatherEntity>()
        apiService.getWeatherByCode(cityCode, OPEN_WEATHER_KEY, language, METRICK_UNIT_SYSTEM)
                .enqueue(object : Callback<WeatherEntity> {
                    override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                        data.value = null
                    }

                    override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                        if (response.isSuccessful) {
                            val value = response.body()
                            data.value = value
                            saveWeather(value!!)
                        }
                    }
                })
        return data
    }

    override fun getLocalWeatherByCoordinate(lat: Double, lng: Double): LiveData<WeatherEntity> {
        return weatherDao.findBy(lat,lng)
    }

    override fun getLocalWeatherByName(cityName: String): LiveData<WeatherEntity> {
        return weatherDao.findBy(cityName)
    }

    override fun getLocalWeatherById(cityCode: Int): LiveData<WeatherEntity> {
        return weatherDao.findBy(cityCode)
    }

    override fun saveWeather(weather: WeatherEntity) {
        InsertAsyncTaskTask(weatherDao).execute(weather)
    }

    override fun deleteWeather(id: Int) {
        DeleteAsyncTaskTask(weatherDao).execute(id)
    }

    private class InsertAsyncTaskTask internal constructor(private val mAsyncTaskDao: WeatherDao) : AsyncTask<WeatherEntity, Void, Void>() {

        override fun doInBackground(vararg params: WeatherEntity): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTaskTask internal constructor(private val mAsyncTaskDao: WeatherDao) : AsyncTask<Int, Void, Void>() {

        override fun doInBackground(vararg params: Int?): Void? {
            mAsyncTaskDao.deleteById(params[0])
            return null
        }
    }
}