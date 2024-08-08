package com.example.dailystretch.data.repository

import com.example.dailystretch.domain.model.ExerciseUiModel

interface RoutineRepository {
    fun addRoutine(routineName: String, exerciseUiModels: List<ExerciseUiModel>)
}
