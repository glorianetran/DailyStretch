package com.example.dailystretch.presentation.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dailystretch.presentation.ui.DailyStretchTheme
import com.example.dailystretch.presentation.view.composables.DailyStretchAppBar
import com.example.dailystretch.presentation.view.composables.DailyStretchScaffold
import com.example.dailystretch.utils.NavigationRoutes

@Composable
fun HomeScreen(navController: NavHostController) {
    DailyStretchScaffold(
        topBar = {
            DailyStretchAppBar(
                title = "Select Routine",
                actions = {
                    IconButton(onClick = { navController.navigate(NavigationRoutes.SETTINGS) }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        HomeScreenContent(paddingValues)
    }
}

@Composable
fun HomeScreenContent(paddingValues: PaddingValues) {
    Log.d("TESTING", "$paddingValues")
    Column(Modifier.padding(paddingValues)) {
        Text("Home Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DailyStretchTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}
