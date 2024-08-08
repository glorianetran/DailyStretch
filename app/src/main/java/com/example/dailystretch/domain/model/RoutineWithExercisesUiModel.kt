package com.example.dailystretch.domain.model

data class RoutineWithExercisesUiModel(
    val routineName: String = "",
    val routineTime: String = "",
    val exercises: List<ExerciseUiModel> = listOf()
)
