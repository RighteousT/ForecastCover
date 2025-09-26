package com.example.forecastcover.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import com.example.forecastcover.R
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

val placeholderForecast = listOf(
    "Sun, Apr 27" to "Cloudy",
    "Mon, Apr 28" to "Rainy",
    "Tue, Apr 29" to "Partly Cloudy"
)
@Composable
fun DailyForecastScreen(forecasts: List<Pair<String, String>>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
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
                    // Weather Icon
                    Image(
                        painter = painterResource(id = R.drawable.baseline_wb_sunny_24),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Date + Info
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

