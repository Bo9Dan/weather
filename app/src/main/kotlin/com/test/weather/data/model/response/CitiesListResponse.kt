package com.test.weather.data.model.response

import com.test.weather.data.db.entity.CityEntity

data class CitiesListResponse(
        var geonames: List<CityEntity>,
        var totalResultsCount: String
)