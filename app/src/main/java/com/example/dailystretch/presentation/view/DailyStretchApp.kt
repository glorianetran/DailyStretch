package com.example.dailystretch.presentation.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailystretch.presentation.view.screens.AddExerciseScreen
import com.example.dailystretch.presentation.view.screens.AddRoutineScreen
import com.example.dailystretch.presentation.view.screens.HomeScreen
import com.example.dailystretch.presentation.view.screens.SettingsScreen
import com.example.dailystretch.presentation.view.screens.WorkoutScreen
import com.example.dailystretch.presentation.viewmodel.AddRoutineViewModel
import com.example.dailystretch.presentation.viewmodel.GetRoutineViewModel
import com.example.dailystretch.utils.NavigationRoutes.ADD_ROUTINE
import com.example.dailystretch.utils.NavigationRoutes.ADD_EXERCISE
import com.example.dailystretch.utils.NavigationRoutes.HOME
import com.example.dailystretch.utils.NavigationRoutes.SETTINGS
import com.example.dailystretch.utils.NavigationRoutes.WORKOUT

@Composable
fun DailyStretchApp() {
    val navController = rememberNavController()
    val addRoutineViewModel: AddRoutineViewModel = hiltViewModel()
    val getRoutineViewModel: GetRoutineViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) { HomeScreen(navController, getRoutineViewModel) }
        composable(ADD_ROUTINE) { AddRoutineScreen(navController, addRoutineViewModel) }
        composable(SETTINGS) { SettingsScreen(navController) }
        composable(ADD_EXERCISE) { AddExerciseScreen(navController, addRoutineViewModel) }
        composable(WORKOUT) { WorkoutScreen(navController) }
    }
}
