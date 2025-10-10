package com.example.forecastcover.models

// The root data class that combines current weather and forecast list
data class Weather(
    val current: Current,
    val forecast: List<Forecast>
)

// Represents the current weather conditions
data class Current(
    val location: String,
    val condition: String,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: Int,
    val windDirection: String
)

// Represents each day's forecast details
data class Forecast (
    val date: String,
    val condition: String,
    val highTemp: Int,
    val lowTemp: Int
)
