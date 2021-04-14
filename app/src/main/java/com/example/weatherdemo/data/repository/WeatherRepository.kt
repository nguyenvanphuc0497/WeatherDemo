package com.example.weatherdemo.data.repository

import com.example.weatherdemo.data.WeatherApiService
import com.example.weatherdemo.data.network.ResultWrapper
import com.example.weatherdemo.data.network.SafeApi
import com.example.weatherdemo.data.vo.City

/**
 * Create by Nguyen Van Phuc on 4/13/21
 */
class WeatherRepository(private val weatherApi: WeatherApiService) : SafeApi() {
    suspend fun getWeatherByTitleCity(city: String): ResultWrapper<List<City>> = safeApiCall {
        weatherApi.getWeatherByTitleCity(city)
    }
}
