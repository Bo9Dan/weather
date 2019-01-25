package com.test.weather.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.databinding.ActivityMainBinding
import com.test.weather.ui.base.BaseActivity
import com.test.weather.ui.detail.DetailActivity
import com.test.weather.ui.search.CitySearchActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    companion object {
        private const val MY_PERMISSION_ACCESS_LOCATION = 1
        private const val ADD_CITY_REQUEST_CODE: Int = 500

        fun getStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()
        bindData()
        subscribeToChanges()
    }

    private fun bindData() {
        binding.toolbarWA.title = getString(R.string.your_cities)

        binding.rvCities.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.btnAddCity.setOnClickListener {
            startActivityForResult(CitySearchActivity.getStartIntent(this), ADD_CITY_REQUEST_CODE)
        }
        binding.btnCurrentPosition.setOnClickListener {
            if (hasLocationPermission()) {
                startActivity(DetailActivity.getStartIntent(this, ""))
            } else requestLocationPermission()
        }
        viewModel.errorClickListener = View.OnClickListener {
            hideError()
//            requestLocationPermission()
        }
    }

    private fun subscribeToChanges() {
        viewModel.loadSavedPlaces().observe(this, viewModel)

        viewModel.deleteCity.observe(this, Observer {
            AlertDialog.Builder(this)
                    .setTitle(it.name)
                    .setMessage(R.string.remove_this_city)
                    .setNegativeButton(R.string.cancel, { dialog, which -> dialog.dismiss() })
                    .setPositiveButton(R.string.ok, { dialog, which ->
                        viewModel.remove(it)
                    }).show()
        })
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                MY_PERMISSION_ACCESS_LOCATION
        )
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (requestCode == MY_PERMISSION_ACCESS_LOCATION)
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                showError("Please, set location manually in settings")
    }

}