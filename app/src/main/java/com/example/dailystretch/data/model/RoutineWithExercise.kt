package com.example.dailystretch.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class RoutineWithExercises(
    @Embedded val routine: RoutineEntity,
    @Relation(
        parentColumn = "routineId",
        entityColumn = "parentRoutineId"
    )
    val exercises: List<ExerciseEntity>
)
