package com.example.dailystretch.data.repository

import com.example.dailystretch.domain.model.ExerciseUiModel
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(): RoutineRepository {
    override fun addRoutine(routineName: String, exerciseUiModels: List<ExerciseUiModel>) {
    }
}
