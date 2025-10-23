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
            contentDescription = "Sky Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Foreground weather info
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Weather icon (placeholder icon for now)
            Image(
                painter = painterResource(id = R.drawable.baseline_cloud_24),
                contentDescription = current.condition.text,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dynamic Weather details
            Text(text = "${current.temp_c}°C")
            Text(text = "Feels like ${current.feelslike_c}°C")
            Text(text = current.condition.text)
            Text(text = "Wind ${current.wind_dir} ${current.wind_kph} kph")
        }
    }
}
