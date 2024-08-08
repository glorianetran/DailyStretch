package com.example.dailystretch.presentation.view.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailystretch.domain.model.ExerciseUiModel
import com.example.dailystretch.presentation.view.composables.DailyStretchAppBar
import com.example.dailystretch.presentation.view.composables.DailyStretchScaffold
import com.example.dailystretch.presentation.viewmodel.AddRoutineViewModel
import com.example.dailystretch.utils.NavigationRoutes

@Composable
fun AddRoutineScreen(
    navController: NavHostController,
    viewModel: AddRoutineViewModel
) {
    val removeItem: (ExerciseUiModel) -> Unit = { exercise ->
        viewModel.removeItem(exercise)
    }
    val exerciseList by viewModel.exerciseUiModelList.collectAsState()

    DailyStretchScaffold(
        topBar = {
            DailyStretchAppBar(
                title = "Add Routine",
                onNavigationClick = {
                    viewModel.clearList()
                    navController.navigateUp()
                },
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                actions = {
                    IconButton(onClick = { viewModel }) {
                        Icon(imageVector = Icons.Filled.Done, contentDescription = "Add routine")
                    }
                }
            )
        },
        fab = {
            ExtendedFloatingActionButton(onClick = { navController.navigate(NavigationRoutes.ADD_EXERCISE) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Exercise")
                Text(text = "Add Exercise")
            }
        }
    ) { paddingValues ->
        AddRoutineContent(paddingValues, exerciseList, removeItem)
    }

    BackHandler {
        viewModel.clearList()
        navController.navigateUp()
    }
}

@Composable
fun AddRoutineContent(
    paddingValues: PaddingValues,
    exerciseUiModelList: List<ExerciseUiModel>,
    removeItem: (ExerciseUiModel) -> Unit
) {
    var routineNameText by remember { mutableStateOf("") }

    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        Text(text = "Routine", fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = routineNameText,
            onValueChange = { newText -> routineNameText = newText },
            label = { Text("Routine name") },
            placeholder = { Text(text = "Enter routine name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Exercises", fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(exerciseUiModelList) { exercise ->
                ExerciseCardView(exercise) {
                    removeItem.invoke(exercise)
                }
            }
        }
    }
}

@Composable
fun ExerciseCardView(exerciseUiModel: ExerciseUiModel, deleteClicked: () -> Unit) {
    Card {
        Row (horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = exerciseUiModel.exerciseName)
                if (!exerciseUiModel.exerciseDesc.isNullOrEmpty()) {
                    Text(text = exerciseUiModel.exerciseDesc)
                }
                if (!exerciseUiModel.items.isNullOrEmpty()) {
                    Text(text = "Items: ${exerciseUiModel.items}")
                }
                Text(text = "Prep time: ${exerciseUiModel.prepTime}")
                Text(text = "Rest time: ${exerciseUiModel.restTime}")
            }
            Column {
                IconButton(onClick = { deleteClicked.invoke() }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Exercise")
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
fun AddRoutineScreenPreview() {
    val exerciseUiModel = ExerciseUiModel("0", "Leg stretch", null, "Block", "10", "10")
    AddRoutineContent(
        PaddingValues(),
        listOf(exerciseUiModel),
        {}
    )
}

