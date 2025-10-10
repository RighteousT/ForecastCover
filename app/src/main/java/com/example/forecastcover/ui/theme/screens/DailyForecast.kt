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
import com.example.forecastcover.models.Forecast

@Composable
fun DailyForecastScreen(forecasts: List<Forecast>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        forecasts.forEach { forecast ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //  Icon based on weather condition
                    val iconRes = when {
                        forecast.condition.contains("Partly Cloudy", ignoreCase = true) -> R.drawable.partly_cloudy
                        forecast.condition.contains("Cloudy", ignoreCase = true) -> R.drawable.baseline_cloud_24
                        forecast.condition.contains("Rain", ignoreCase = true) -> R.drawable.baseline_water_drop_24
                        forecast.condition.contains("Sunny", ignoreCase = true) -> R.drawable.baseline_wb_sunny_24
                        else -> R.drawable.baseline_wb_sunny_24
                    }



                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = forecast.condition,
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
                            text = "${forecast.condition} - High ${forecast.highTemp}° / Low ${forecast.lowTemp}°",
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }
                }
            }
        }
    }
}
