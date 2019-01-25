package com.test.weather.ui.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.test.weather.R
import com.test.weather.ui.base.BaseActivityViewModel
import com.test.weather.data.db.entity.WeatherEntity
import com.test.weather.data.provider.WeatherProvider
import com.test.weather.internal.IconConverter
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(private val provider: WeatherProvider) : BaseActivityViewModel(), Observer<WeatherEntity> {

    val status: MutableLiveData<String> = MutableLiveData()
    val temperature: MutableLiveData<String> = MutableLiveData()
    val windSpeed: MutableLiveData<String> = MutableLiveData()
    val humidity: MutableLiveData<String> = MutableLiveData()
    val pressure: MutableLiveData<String> = MutableLiveData()
    val cityName: MutableLiveData<String> = MutableLiveData()
    val cityID: MutableLiveData<Int> = MutableLiveData()
    val windDirection: MutableLiveData<Int> = MutableLiveData()
    val icon: MutableLiveData<Int> = MutableLiveData()

    private val dfCoordinates = DecimalFormat("#.####")
    private val dfTemperature = DecimalFormat("#")

    init {
        errorClickListener = View.OnClickListener {  }
    }

    fun weatherLiveDataFor(city: String): LiveData<WeatherEntity> {
        cityName.value = city
        return provider.getWeatherByName(city)
    }

    fun weatherLiveDataFor(city: Int): LiveData<WeatherEntity> {
        cityID.value = city
        return provider.getWeatherById(city)
    }

    fun weatherLiveDataFor(lat: Double, lng: Double): LiveData<WeatherEntity> {
        dfCoordinates.roundingMode = RoundingMode.CEILING

        cityName.value = dfCoordinates.format(lat) + " | " + dfCoordinates.format(lng)
        return provider.getWeatherByCoordinate(lat, lng)
    }

    override fun onChanged(data: WeatherEntity?) {
        if (data == null) onRetrieveError(Throwable())
        else onRetrieveSuccess(data)
    }

    private fun onRetrieveError(it: Throwable) {
        errorMessage.value = it.localizedMessage
    }

    private fun onRetrieveSuccess(data: WeatherEntity) {
        dfTemperature.roundingMode = RoundingMode.CEILING
        icon.value = IconConverter.getIcon(data.weather[0].icon)
        temperature.value = dfTemperature.format(data.main.temp) + "℃"
        status.value = data.weather[0].description

        windDirection.value = IconConverter.getWindDirection(data.wind.deg)

        windSpeed.value = String.format("%.2f", data.wind.speed) + " m/s"
        pressure.value = String.format("%.2f", data.main.pressure * 0.750063755419211) + " mmHg"
        humidity.value = data.main.humidity.toString()
    }
}