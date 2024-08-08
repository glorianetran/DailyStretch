package com.example.dailystretch.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailystretch.domain.model.RoutineUiModel
import com.example.dailystretch.presentation.ui.DailyStretchTheme
import com.example.dailystretch.presentation.view.composables.DailyStretchAppBar
import com.example.dailystretch.presentation.view.composables.DailyStretchScaffold
import com.example.dailystretch.presentation.viewmodel.GetRoutineViewModel
import com.example.dailystretch.utils.NavigationRoutes

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: GetRoutineViewModel
) {
    viewModel.getAllRoutines()

    val routineList by viewModel.routineList.collectAsState()

    DailyStretchScaffold(
        topBar = {
            DailyStretchAppBar(
                title = "Select Routine",
                actions = {
                    IconButton(onClick = { navController.navigate(NavigationRoutes.ADD_ROUTINE) }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Routine")
                    }

                    IconButton(onClick = { navController.navigate(NavigationRoutes.SETTINGS) }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        HomeScreenContent(paddingValues, routineList)
    }
}

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    routineList: List<RoutineUiModel>
) {
    Column(Modifier.padding(paddingValues)) {
        LazyColumn {
            items(routineList) { routine ->
                Card(modifier = Modifier.fillParentMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(text = routine.name, fontSize = 18.sp)
                        Text(text = "Time: ${routine.minutesAndSeconds}")
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DailyStretchTheme {
        HomeScreenContent(paddingValues = PaddingValues(), listOf(RoutineUiModel("Workout 1", "3:55")))
    }
}
