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
import com.example.forecastcover.models.ForecastDay

@Composable
fun DailyForecastScreen(forecasts: List<ForecastDay>) {
    Box(modifier = Modifier.fillMaxSize()) {

        // Background image
        Image(
            painter = painterResource(id = R.drawable.weather_background),
            contentDescription = "Sky Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Forecast list on top
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            forecasts.forEach { forecast ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(all = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val iconRes = when {
                            forecast.day.condition.text.contains("Partly Cloudy", ignoreCase = true) -> R.drawable.partly_cloudy
                            forecast.day.condition.text.contains("Cloudy", ignoreCase = true) -> R.drawable.baseline_cloud_24
                            forecast.day.condition.text.contains("Rain", ignoreCase = true) -> R.drawable.baseline_water_drop_24
                            forecast.day.condition.text.contains("Sunny", ignoreCase = true) -> R.drawable.baseline_wb_sunny_24
                            else -> R.drawable.baseline_wb_sunny_24
                        }

                        Image(
                            painter = painterResource(id = iconRes),
                            contentDescription = forecast.day.condition.text,
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = forecast.date,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "${forecast.day.condition.text} - High ${forecast.day.maxtemp_c}° / Low ${forecast.day.mintemp_c}°",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
