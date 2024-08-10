package com.example.dailystretch.data.repository

import com.example.dailystretch.data.dao.RoutineDao
import com.example.dailystretch.data.model.RoutineEntity
import com.example.dailystretch.data.model.RoutineWithExercisesEntity
import com.example.dailystretch.data.model.toRoutineWithExercisesUiModel
import com.example.dailystretch.domain.model.ExerciseUiModel
import com.example.dailystretch.domain.model.RoutineWithExercisesUiModel
import com.example.dailystretch.domain.model.toExerciseEntity
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(
    private val dao: RoutineDao
): RoutineRepository {

    override suspend fun addRoutine(routineName: String, exerciseUiModels: List<ExerciseUiModel>) {
        var timeInSecondsSum = 0
        exerciseUiModels.forEach { exercise ->
            timeInSecondsSum += exercise.durationTime.toInt()
        }

        val routineEntity = RoutineEntity(
            name = routineName,
            time = timeInSecondsSum.toString()
        )

        val parentRoutineId = dao.insertRoutine(routineEntity)

        val exerciseEntityList = exerciseUiModels.map { exerciseUiModel -> exerciseUiModel.toExerciseEntity(parentRoutineId) }
        dao.insertExercises(exerciseEntityList)
    }

    override suspend fun getAllRoutines(): List<RoutineEntity> {
        return dao.getAllRoutines()
    }

    override suspend fun getRoutineWithExercises(id: Long): RoutineWithExercisesUiModel {
        val routineWithExercises = dao.getRoutineWithExercises(id)
        return routineWithExercises.toRoutineWithExercisesUiModel()
    }

    override suspend fun deleteRoutineById(id: Long) {
        return dao.deleteRoutineById(id)
    }
}
