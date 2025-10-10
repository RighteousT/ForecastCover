package com.example.forecastcover

import androidx.lifecycle.ViewModel
import com.example.forecastcover.models.Current
import com.example.forecastcover.models.Forecast
import com.example.forecastcover.models.Weather


class MainViewModel : ViewModel() {
    //Placeholder data
    val weather: Weather

    init {
        val currentWeather = Current (
            location = "Halifax, Nova Scotia",
            condition = "Overcast",
            temperature = 6,
            feelsLike = 2,
            windSpeed = 18,
            windDirection = "SW"
        )
        val forecastList = listOf(
            Forecast("Sun, Apr 27", "Cloudy", highTemp = 8, lowTemp = 3),
            Forecast("Mon, Apr 28", "Rainy", highTemp = 7, lowTemp = 2),
            Forecast("Tue, Apr 29", "Partly Cloudy", highTemp = 10, lowTemp = 4)

        )

        weather = Weather(
            current = currentWeather,
            forecast = forecastList
        )
    }
}
