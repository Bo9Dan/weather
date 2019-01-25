package com.test.weather.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.test.weather.R
import com.test.weather.databinding.ActivityDetailedBinding
import com.test.weather.ui.base.BaseActivity
import java.lang.Exception

class DetailActivity : BaseActivity<ActivityDetailedBinding, DetailActivityViewModel>() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun getLayoutResource(): Int = R.layout.activity_detailed

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val data: String = intent.extras.getString("CityName", "")

        if (data.isEmpty()) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null)
                viewModel.weatherLiveDataFor(location.latitude, location.longitude)
                        .observe(this, viewModel)
                else showError("Cannot find your location")
            }.addOnFailureListener { exception: Exception -> exception.printStackTrace() }
        } else {
            viewModel.weatherLiveDataFor(data).observe(this, viewModel)
        }
    }

    companion object {
        fun getStartIntent(context: Context, city: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("CityName", city)
            return intent
        }
    }
}