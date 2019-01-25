package com.test.weather.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.test.weather.ui.base.BaseActivityViewModel
import com.test.weather.data.db.entity.CityEntity
import com.test.weather.data.provider.CitiesProvider
import javax.inject.Inject
import kotlin.collections.ArrayList

class CitySearchActivityViewModel @Inject constructor(private val provider: CitiesProvider) : BaseActivityViewModel(), Observer<List<CityEntity>> {

    val mutableInputName: MutableLiveData<String> = MutableLiveData()
    val selectedPlace: MutableLiveData<CityEntity> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    var placeList: MutableList<CityEntity> = ArrayList()
    val citiesListAdapter: CitiesListAdapter = CitiesListAdapter {
        selectedPlace.value = it
    }

    init {
        loadingVisibility.value = View.GONE
    }

    fun saveCityToDB(cityEntity: CityEntity) {
        provider.insertCity(cityEntity)
    }

    fun findCityByName(name: String): LiveData<List<CityEntity>> {
        return provider.searchCityByName(name)
    }

    override fun onChanged(t: List<CityEntity>?) {
        if (t != null) {
            placeList.clear()
            placeList.addAll(t)
            onRetrieveListSuccess(placeList)
        } else {
            onRetrieveListError()
        }
    }

    private fun onRetrieveListError() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveListSuccess(result: MutableList<CityEntity>) {
        citiesListAdapter.citiesList = result
        citiesListAdapter.notifyDataSetChanged()
        loadingVisibility.value = View.GONE
    }
}
