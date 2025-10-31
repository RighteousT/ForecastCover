package com.example.forecastcover

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forecastcover.ui.screens.CurrentWeatherScreen
import com.example.forecastcover.ui.screens.DailyForecastScreen
import com.example.forecastcover.ui.theme.ForecastCoverTheme
import com.example.forecastcover.viewmodel.MainViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Forecast Cover") }
    )
}

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val weather by viewModel.weather.collectAsState()
    val navController = rememberNavController()
    val context = LocalContext.current

    // Ask for location permission
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            viewModel.fetchLocationAndWeather()
        } else {
            Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
            viewModel.fetchWeatherByCity("Halifax") // fallback
        }
    }

    // Requests permission when the app starts
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.fetchLocationAndWeather()
        } else {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

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
