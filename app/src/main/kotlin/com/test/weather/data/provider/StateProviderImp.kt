package com.test.weather.data.provider

import android.content.Context
import android.location.LocationManager
import android.provider.Settings

class StateProviderImp(context: Context) : StateProvider {

    var lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var cr = context.contentResolver!!

    override fun isAirplaneModeOn(): Boolean {
        return Settings.System.getInt(cr, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }

    override fun isGPSOn(): Boolean {
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    override fun isNetworkOn(): Boolean {
        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}