package com.example.dailystretch.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailystretch.domain.model.Exercise
import com.example.dailystretch.presentation.view.composables.DailyStretchAppBar
import com.example.dailystretch.presentation.view.composables.DailyStretchScaffold
import com.example.dailystretch.presentation.viewmodel.AddRoutineViewModel


@Composable
fun AddExerciseScreen(
    navController: NavHostController,
    addRoutineViewModel: AddRoutineViewModel
) {
    var isAddClicked by remember { mutableStateOf(false) }
    DailyStretchScaffold(
        topBar = {
            DailyStretchAppBar(
                title = "Add Exercise",
                onNavigationClick = { navController.navigateUp() },
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                actions = {
                    IconButton(onClick = {
                        isAddClicked = true
                    }) {
                        Icon(imageVector = Icons.Filled.Done, contentDescription = "Add routine")
                    }
                }
            )
        }
    ) { paddingValues ->
        AddExerciseContent(
            paddingValues = paddingValues,
            isAddClicked = isAddClicked,
            isAddClickedHandled = { exercise ->
                addRoutineViewModel.addExercise(exercise)
                isAddClicked = false
                navController.navigateUp()
            }
        )
    }
}

@Composable
fun AddExerciseContent(
    paddingValues: PaddingValues,
    isAddClicked: Boolean,
    isAddClickedHandled: (Exercise) -> Unit
) {
    var exerciseName by remember { mutableStateOf("") }
    var exerciseDesc by remember { mutableStateOf("") }
    var items by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
    var restTime by remember { mutableStateOf("") }

    LaunchedEffect(isAddClicked) {
        if (isAddClicked) {
            val exercise = Exercise(
                exerciseName = exerciseName,
                exerciseDesc = exerciseDesc,
                items = items,
                prepTime = prepTime,
                restTime = restTime
            )
            isAddClickedHandled.invoke(exercise)
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = exerciseName,
            onValueChange = { newText -> exerciseName = newText },
            label = { Text("Exercise name") },
            placeholder = { Text(text = "Exercise name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = exerciseDesc,
            onValueChange = { newText -> exerciseDesc = newText },
            label = { Text("Exercise Description") },
            placeholder = { Text(text = "Exercise Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = items,
            onValueChange = { newText -> items = newText },
            label = { Text("Items") },
            placeholder = { Text(text = "Items") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            OutlinedTextField(
                value = prepTime,
                onValueChange = { newText -> prepTime = newText },
                label = { Text("Prep time") },
                placeholder = { Text(text = "Prep Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = restTime,
                onValueChange = { newText -> restTime = newText },
                label = { Text("Rest Time") },
                placeholder = { Text(text = "Rest Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddExerciseScreenPreview() {
    AddExerciseContent(PaddingValues(), false, {})
}
