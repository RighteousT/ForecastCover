package com.example.forecastcover.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.forecastcover.models.WeatherResponse
import com.example.forecastcover.services.RetrofitInstance
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather = _weather.asStateFlow()

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    fun fetchLocationAndWeather() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val lat = location.latitude
                val lon = location.longitude
                Log.d("MainViewModel", "User location: $lat, $lon")
                fetchWeatherByCoords(lat, lon)
            } else {
                Log.e("MainViewModel", "Location null — using Halifax fallback")
                fetchWeatherByCity("Halifax")
            }
        }.addOnFailureListener {
            Log.e("MainViewModel", "Failed to get location: ${it.message}")
            fetchWeatherByCity("Halifax")
        }
    }

    fun fetchWeatherByCity(city: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getForecast(
                    apiKey = "45a5b79e3e2f4d0d812174751251510",
                    city = city
                )
                _weather.value = response
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching weather by city", e)
            }
        }
    }

    private fun fetchWeatherByCoords(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getForecast(
                    apiKey = "45a5b79e3e2f4d0d812174751251510",
                    city = "$lat,$lon"
                )
                _weather.value = response
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching weather by coords", e)
            }
        }
    }
}
