package com.example.dailystretch.domain.model

data class RoutineWithExercisesUiModel(
    val id: Long = 0L,
    val routineName: String = "",
    val routineTime: String = "",
    val numberOfExercises: String = "",
    val exercises: List<ExerciseUiModel> = listOf()
)
