package com.example.forecastcover.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import com.example.forecastcover.R
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

val placeholderForecast = listOf(
    "Sun, Apr 27" to "Cloudy",
    "Mon, Apr 28" to "Rainy",
    "Tue, Apr 29" to "Partly Cloudy"
)

@Composable
fun DailyForecastScreen(forecasts: List<Pair<String, String>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        forecasts.forEach { (date, info) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val iconRes = when {
                        info.contains("Cloudy", ignoreCase = true) -> R.drawable.baseline_cloud_24
                        info.contains("Rainy", ignoreCase = true) -> R.drawable.baseline_water_drop_24
                        info.contains("Sunny", ignoreCase = true) -> R.drawable.baseline_wb_sunny_24
                        else -> R.drawable.baseline_wb_sunny_24 // fallback
                    }

                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Date and Info
                    Column {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = info,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
