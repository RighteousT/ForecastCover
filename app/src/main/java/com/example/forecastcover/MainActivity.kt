package com.example.forecastcover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forecastcover.ui.theme.ForecastCoverTheme


import com.example.forecastcover.ui.screens.CurrentWeatherScreen
import com.example.forecastcover.ui.screens.DailyForecastScreen
import com.example.forecastcover.ui.screens.placeholderForecast

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForecastCoverTheme { DisplayUI() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "Halifax, Nova Scotia") }
    )
}

@Composable
fun DisplayUI() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = {
            NavigationBar {
                val current = navController.currentBackStackEntry?.destination?.route
                NavigationBarItem(
                    selected = current == "current",
                    onClick = { navController.navigate("current") },
                    icon = { Icon(Icons.Filled.Cloud, contentDescription = "Current") },
                    label = { Text("Now") }
                )
                NavigationBarItem(
                    selected = current == "daily",
                    onClick = { navController.navigate("daily") },
                    icon = { Icon(Icons.Filled.CalendarToday, contentDescription = "Daily") },
                    label = { Text("Daily") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "current",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("current") { CurrentWeatherScreen() }
            composable("daily") { DailyForecastScreen(forecasts = placeholderForecast) }
        }
    }
}
