package com.example.forecastcover


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.forecastcover.ui.screens.CurrentWeatherScreen
import com.example.forecastcover.ui.theme.ForecastCoverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForecastCoverTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    // ✅ Show your CurrentWeatherScreen
                    CurrentWeatherScreen()
                }
            }
        }
    }
}


