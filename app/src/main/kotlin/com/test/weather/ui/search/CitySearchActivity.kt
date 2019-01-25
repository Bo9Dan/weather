package com.test.weather.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.ui.base.BaseActivity
import com.test.weather.databinding.ActivityCitySearchBinding
import kotlinx.android.synthetic.main.activity_city_search.*

class CitySearchActivity : BaseActivity<ActivityCitySearchBinding, CitySearchActivityViewModel>() {

    override fun getLayoutResource(): Int = R.layout.activity_city_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvCitiesSA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        toolbarCA.title = getString(R.string.city_search_title)

        viewModel.mutableInputName.observe(this, Observer { cityName ->
            if (!(cityName == null || cityName.toString().isEmpty()))
                if (cityName.length > 2)
                    viewModel.findCityByName(cityName).observe(this, viewModel)
        })

        viewModel.selectedPlace.observe(this, Observer { geoname ->
            if (geoname != null) {
                AlertDialog.Builder(this)
                        .setTitle(geoname.name)
                        .setMessage(R.string.select_this_city)
                        .setNegativeButton(R.string.cancel, { dialog, which -> dialog.dismiss() })
                        .setPositiveButton(R.string.ok, { dialog, which ->
                            viewModel.saveCityToDB(geoname)
                            onBackPressed()
                        })
                        .show()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, CitySearchActivity::class.java)
        }
    }
}