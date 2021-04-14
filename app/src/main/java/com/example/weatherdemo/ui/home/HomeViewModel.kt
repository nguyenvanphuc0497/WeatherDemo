package com.example.weatherdemo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherdemo.data.local.WeatherDAO
import com.example.weatherdemo.data.network.ResultWrapper
import com.example.weatherdemo.data.repository.WeatherRepository
import com.example.weatherdemo.data.vo.City
import com.example.weatherdemo.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Create by Nguyen Van Phuc on 4/14/21
 */
class HomeViewModel : BaseViewModel(), KoinComponent {
    private val weatherRepository by inject<WeatherRepository>()
    private val weatherDao by inject<WeatherDAO>()

    private val cityResult: MutableLiveData<List<City>> by lazy { MutableLiveData() }

    private suspend fun getListCityFromServer(textQuery: String) {
        when (val response = weatherRepository.getWeatherByTitleCity(textQuery)) {
            is ResultWrapper.Success -> {
                cityResult.postValue(response.data)
                weatherDao.insertAll(response.data)
            }
            is ResultWrapper.NetworkError -> {
                postApiException("Network Error")
            }
            is ResultWrapper.Error -> {
                postApiException(response.msg ?: "Generic Error")
            }
        }
    }

    private suspend fun getListCityFromLocal(textQuery: String) {
        cityResult.postValue(weatherDao.getListCityBy(textQuery))
    }

    internal fun getListCityBy(textQuery: String) {
        viewModelScope.launch {
            if (weatherDao.isCityExists(textQuery)) {
                getListCityFromLocal(textQuery)
            } else {
                getListCityFromServer(textQuery)
            }
        }
    }

    internal fun clearQuerySearch() {
        cityResult.postValue(listOf())
    }

    internal fun getWeatherResult() = cityResult
}
