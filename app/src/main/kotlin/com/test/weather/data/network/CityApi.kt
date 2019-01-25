package com.test.weather.data.network

import com.test.weather.data.model.response.CitiesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {
    @GET("search")
    fun getCitiesByName(@Query("q") cityName: String,
                        @Query("lang") locale: String,
                        @Query("maxRows") maxRows: Int,
                        @Query("style") style: String,
                        @Query("username") userName: String,
                        @Query("type") type: String,
                        @Query("fuzzy") fuzzy: Double,
                        @Query("featureClass") feature: String,
                        @Query("orderby") orderBy: String,
                        @Query("isNameRequired") isName: Boolean): Call<CitiesListResponse>
}