package com.test.weather.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.test.weather.R
import com.test.weather.ui.base.BaseActivityViewModel
import com.test.weather.data.db.entity.CityEntity
import com.test.weather.data.provider.CitiesProvider
import com.test.weather.ui.detail.DetailActivity
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val provider: CitiesProvider) : BaseActivityViewModel(), Observer<List<CityEntity>> {

    val emptyContainerVisibility: MutableLiveData<Int> = MutableLiveData()
    val mainContainerVisibility: MutableLiveData<Int> = MutableLiveData()
    val deleteCity: MutableLiveData<CityEntity> = MutableLiveData()

    private val allGeoNames: LiveData<List<CityEntity>>

    val adapter: SavedPlacesAdapter = SavedPlacesAdapter(
            selectItem = {
                val bundle = Bundle()
                bundle.putString("CityName", it.name)
                activityToStart.value = Pair(DetailActivity::class, bundle)
            },
            deleteItem = {
                deleteCity.value = it
            }
    )

    init {
        emptyContainerVisibility.value = View.GONE
        mainContainerVisibility.value = View.GONE
        allGeoNames = provider.retrieveAllSavedPlaces()
    }

    fun loadSavedPlaces(): LiveData<List<CityEntity>> {
        return allGeoNames
    }

    private fun onRetrieveCitiesListSuccess(result: List<CityEntity>?) {
        if (result == null || result.isEmpty()) {
            emptyContainerVisibility.value = View.VISIBLE
            mainContainerVisibility.value = View.GONE
        } else {
            emptyContainerVisibility.value = View.GONE
            mainContainerVisibility.value = View.VISIBLE
        }
        adapter.citiesList = result!!
        adapter.notifyDataSetChanged()
    }

    fun remove(cityEntity: CityEntity) {
        provider.deleteCity(cityEntity)
    }

    override fun onChanged(t: List<CityEntity>?) {
        onRetrieveCitiesListSuccess(t!!)
    }
}