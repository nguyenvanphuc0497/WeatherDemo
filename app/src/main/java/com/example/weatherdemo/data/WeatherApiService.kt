package com.example.weatherdemo.data

import com.example.weatherdemo.data.vo.City
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Create by Nguyen Van Phuc on 4/13/21
 */
interface WeatherApiService {
    @GET("/api/location/search/")
    suspend fun getWeatherByTitleCity(@Query("query") query: String): List<City>
}
