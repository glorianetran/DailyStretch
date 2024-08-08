package com.example.dailystretch.data.repository

import com.example.dailystretch.data.model.RoutineEntity
import com.example.dailystretch.domain.model.ExerciseUiModel

interface RoutineRepository {
    suspend fun addRoutine(routineName: String, exerciseUiModels: List<ExerciseUiModel>)

    suspend fun getAllRoutines(): List<RoutineEntity>

}
