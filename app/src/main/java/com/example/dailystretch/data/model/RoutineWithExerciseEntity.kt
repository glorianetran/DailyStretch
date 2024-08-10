package com.example.dailystretch.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.dailystretch.domain.model.RoutineWithExercisesUiModel
import com.example.dailystretch.utils.toMinutesAndSecondsString

data class RoutineWithExercisesEntity(
    @Embedded val routine: RoutineEntity,
    @Relation(
        parentColumn = "routineId",
        entityColumn = "parentRoutineId"
    )
    val exercises: List<ExerciseEntity>
)

fun RoutineWithExercisesEntity.toRoutineWithExercisesUiModel(): RoutineWithExercisesUiModel {
    return RoutineWithExercisesUiModel(
        id = this.routine.routineId,
        routineName = this.routine.name,
        routineTime = this.routine.time.toInt().toMinutesAndSecondsString(),
        numberOfExercises = this.exercises.size.toString(),
        exercises = exercises.map { it.toExerciseUiModel() }
    )
}

