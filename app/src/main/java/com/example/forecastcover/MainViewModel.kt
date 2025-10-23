package com.example.forecastcover.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forecastcover.models.WeatherResponse
import com.example.forecastcover.services.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather = _weather.asStateFlow()

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getForecast(
                    apiKey = "45a5b79e3e2f4d0d812174751251510",
                    city = "Halifax"
                )
                _weather.value = response
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching weather data", e)
            }
        }
    }
}
