package com.example.forecastcover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.forecastcover.ui.theme.ForecastCoverTheme
import com.example.forecastcover.ui.screens.CurrentWeatherScreen
import com.example.forecastcover.ui.screens.DailyForecastScreen
import com.example.forecastcover.viewmodel.MainViewModel
import androidx.compose.animation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForecastCoverTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Halifax, Nova Scotia") }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val weather by viewModel.weather.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                NavigationBarItem(
                    selected = currentRoute == "current",
                    onClick = { navController.navigate("current") },
                    icon = { Icon(Icons.Filled.Cloud, contentDescription = "Current") },
                    label = { Text("Now") }
                )
                NavigationBarItem(
                    selected = currentRoute == "daily",
                    onClick = { navController.navigate("daily") },
                    icon = { Icon(Icons.Filled.CalendarToday, contentDescription = "Daily") },
                    label = { Text("Daily") }
                )
            }
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = "current",
            modifier = Modifier.padding(innerPadding),
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) {
            composable("current") {
                weather?.let {
                    CurrentWeatherScreen(current = it.current)
                } ?: Text("Loading current weather...")
            }
            composable("daily") {
                weather?.let {
                    DailyForecastScreen(forecasts = it.forecast.forecastday)
                } ?: Text("Loading forecast...")
            }
        }
    }
}