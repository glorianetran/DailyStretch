package com.example.dailystretch.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailystretch.presentation.view.screens.AddStretchScreen
import com.example.dailystretch.presentation.view.screens.HomeScreen
import com.example.dailystretch.presentation.view.screens.SettingsScreen
import com.example.dailystretch.utils.NavigationRoutes.ADD_STRETCH
import com.example.dailystretch.utils.NavigationRoutes.HOME
import com.example.dailystretch.utils.NavigationRoutes.SETTINGS

@Composable
fun DailyStretchApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) { HomeScreen(navController) }
        composable(ADD_STRETCH) { AddStretchScreen(navController) }
        composable(SETTINGS) { SettingsScreen(navController) }
    }
}
