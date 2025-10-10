package com.example.forecastcover.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.forecastcover.R
import com.example.forecastcover.models.Current

@Composable
fun CurrentWeatherScreen(current: Current) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.weather_background),
            contentDescription = "Weather background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Foreground weather info
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Weather icon
            Image(
                painter = painterResource(id = R.drawable.baseline_cloud_24),
                contentDescription = current.condition,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dynamic Weather details
            Text(text = current.condition)
            Text(text = "${current.temperature}°C")
            Text(text = "Feels like ${current.feelsLike}°C")
            Text(text = "Wind ${current.windDirection} ${current.windSpeed} kph")

        }
    }
}
