package com.example.dailystretch.presentation.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.dailystretch.presentation.view.composables.DailyStretchAppBar
import com.example.dailystretch.presentation.view.composables.DailyStretchScaffold

@Composable
fun WorkoutScreen(navController: NavHostController) {
    DailyStretchScaffold(
        topBar = {
            DailyStretchAppBar(
                title = "Workout",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        WorkoutScreenContent(paddingValues)
    }
}

@Composable
fun WorkoutScreenContent(paddingValues: PaddingValues) {
    Column (Modifier.padding(paddingValues)){
        Text(text = "Workout Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutScreenPreview() {
    WorkoutScreenContent(PaddingValues())
}
