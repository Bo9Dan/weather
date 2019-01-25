package com.test.weather.internal

import com.test.weather.R

object IconConverter {
    fun getIcon(iconId: String): Int {
        when (iconId) {
            "01d" -> return R.drawable.weather_clear_night
            "01n" -> return R.drawable.weather_sunny
            "02d" -> return R.drawable.vector_drawable_cloudy
            "02n" -> return R.drawable.weather_cloudy_night
            "03d" -> return R.drawable.vector_drawable_cloud
            "03n" -> return R.drawable.vector_drawable_clouds_night
            "04d" -> return R.drawable.vector_drawable_brocken_clouds_day
            "04n" -> return R.drawable.vector_drawable_brocken_clouds_night
            "09d" -> return R.drawable.vector_drawable_rain_day
            "09n" -> return R.drawable.vector_drawable_rain_night
            "10d" -> return R.drawable.vector_drawable_light_rain_day
            "10n" -> return R.drawable.vector_drawable_light_rain_night
            "11d" -> return R.drawable.vector_drawable_storm_day
            "11n" -> return R.drawable.vector_drawable_storm_night
            "13d" -> return R.drawable.vector_drawable_snov_day
            "13n" -> return R.drawable.vector_drawable_snow_night
            "50d" -> return R.drawable.vector_drawable_mist
            "50n" -> return R.drawable.vector_drawable_mist
        }
        return R.drawable.icon_placeholder
    }

    fun getWindDirection(degree: Double): Int {
        var mutDegree = degree
        mutDegree += 180

        if (mutDegree >= 360) {
            mutDegree -= 360
        }

        if (mutDegree >= 337.5 || mutDegree >= 0 && mutDegree < 22.5) {
            return R.string.wind_direction_north
        }
        if (mutDegree >= 22.5 && mutDegree < 67.5) {
            return R.string.wind_direction_north_east
        }
        if (mutDegree >= 67.5 && mutDegree < 112.5) {
            return R.string.wind_direction_east
        }
        if (mutDegree >= 112.5 && mutDegree < 157.5) {
            return R.string.wind_direction_south_east
        }
        if (mutDegree >= 157.5 && mutDegree < 202.5) {
            return R.string.wind_direction_south
        }
        if (mutDegree >= 202.5 && mutDegree < 247.5) {
            return R.string.wind_direction_south_west
        }
        if (mutDegree >= 247.5 && mutDegree < 292.5) {
            return R.string.wind_direction_west
        }
        return if (mutDegree >= 292.5 && mutDegree < 337.5) {
            R.string.wind_direction_north_west
        } else R.string.no_direcrion
    }

}