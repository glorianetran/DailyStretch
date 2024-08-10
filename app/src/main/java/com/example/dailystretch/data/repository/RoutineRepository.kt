package com.example.dailystretch.data.repository

import com.example.dailystretch.data.model.RoutineEntity
import com.example.dailystretch.data.model.RoutineWithExercisesEntity
import com.example.dailystretch.domain.model.ExerciseUiModel
import com.example.dailystretch.domain.model.RoutineWithExercisesUiModel

interface RoutineRepository {
    suspend fun addRoutine(routineName: String, exerciseUiModels: List<ExerciseUiModel>)

    suspend fun getAllRoutines(): List<RoutineEntity>

    suspend fun getRoutineWithExercises(id: Long): RoutineWithExercisesUiModel

    suspend fun deleteRoutineById(id: Long)
}
