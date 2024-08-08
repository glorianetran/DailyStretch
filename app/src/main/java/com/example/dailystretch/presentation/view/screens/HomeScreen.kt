package com.example.dailystretch.presentation.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailystretch.domain.model.RoutineUiModel
import com.example.dailystretch.domain.model.RoutineWithExercisesUiModel
import com.example.dailystretch.presentation.ui.DailyStretchTheme
import com.example.dailystretch.presentation.view.composables.CardFace
import com.example.dailystretch.presentation.view.composables.DailyStretchAppBar
import com.example.dailystretch.presentation.view.composables.DailyStretchFlipCard
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
    val routineWithExercise by viewModel.routineWithExercise.collectAsState()

    var showCard by remember { mutableStateOf(false) }

    val detailsClicked: (Long) -> Unit = { routineId ->
        showCard = true
        viewModel.getRoutineWithExercises(routineId)
    }

    val dismissClicked: () -> Unit = {
        showCard = false
    }

    val navigateEvent: (String) -> Unit = { route ->
        navController.navigate(route)
    }

    Box(Modifier.fillMaxSize()) {
        DailyStretchScaffold(
            topBar = {
                DailyStretchAppBar(
                    title = "Select Routine",
                    actions = {
                        IconButton(onClick = { navController.navigate(NavigationRoutes.ADD_ROUTINE) }) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Routine")
                        }

                        IconButton(onClick = { navController.navigate(NavigationRoutes.SETTINGS) }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            HomeScreenContent(paddingValues, routineList, detailsClicked, navigateEvent)
        }

        if (showCard) {
            DetailsCard(
                dismissClicked,
                navigateEvent,
                routineWithExercise
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    routineList: List<RoutineUiModel>,
    detailsClicked: (Long) -> Unit,
    startWorkoutClicked: (String) -> Unit
) {
    Column(Modifier.padding(paddingValues)) {
        LazyColumn {
            items(routineList) { routine ->
                var cardFace by remember { mutableStateOf(CardFace.Front) }

                DailyStretchFlipCard(
                    cardFace = cardFace,
                    onClick = { cardFace = cardFace.next },
                    modifier = Modifier.fillParentMaxWidth(),
                    front = {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = routine.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Time: ${routine.minutesAndSeconds}")
                        }
                    },
                    back = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { detailsClicked(routine.id) }) {
                                Text("Details")
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Button(onClick = { startWorkoutClicked.invoke(NavigationRoutes.WORKOUT) }) {
                                Text("Start Workout")
                            }
                        }
                    }

                )
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun DetailsCard(
    closeCardAction: () -> Unit,
    startWorkoutClicked: (String) -> Unit,
    routineWithExercise: RoutineWithExercisesUiModel
) {
    Box(Modifier.padding(PaddingValues(16.dp, 32.dp, 16.dp, 32.dp))) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { closeCardAction() }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit Routine"
                        )
                    }

                    IconButton(onClick = { closeCardAction() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Delete Routine"
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = routineWithExercise.routineName,
                        textAlign = TextAlign.Center,
                        fontSize = 48.sp
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = routineWithExercise.routineTime,
                        textAlign = TextAlign.Center,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // list view with exercise names description
                    Spacer(modifier = Modifier.padding(24.dp))

                    val exerciseString = if (routineWithExercise.exercises.size < 2)  {
                        "exercise"
                    } else {
                        "exercises"
                    }

                    Text("${routineWithExercise.numberOfExercises} $exerciseString")
                    Spacer(modifier = Modifier.padding(8.dp))

                    LazyColumn {
                        items(routineWithExercise.exercises) { exercise ->
                            Card(
                                modifier = Modifier.fillParentMaxWidth(),
                                border = BorderStroke(1.dp, color = Color.LightGray)) {
                                Row(
                                    modifier = Modifier.fillParentMaxWidth().padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(exercise.exerciseName)
                                    Text(exercise.durationTime)
                                }
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { closeCardAction() }) {
                        Text("Delete")
                    }
                    Button(
                        onClick = { startWorkoutClicked(NavigationRoutes.WORKOUT) }
                    ) {
                        Text("Start Workout")
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DailyStretchTheme {
        HomeScreenContent(
            paddingValues = PaddingValues(),
            listOf(RoutineUiModel(0L, "Workout 1", "3:55")),
            {},
            {}
        )
    }
}
