package com.example.dailystretch.domain.model

import com.example.dailystretch.data.model.ExerciseEntity


data class ExerciseUiModel(
    val id: String? = null,
    val exerciseName: String,
    val exerciseDesc: String?,
    val items: String?,
    val prepTime: String,
    val restTime: String,
    val durationTime: String
)

fun ExerciseUiModel.toExerciseEntity(parentRoutineId: Long): ExerciseEntity {
    return ExerciseEntity(
        name = this.exerciseName,
        description = this.exerciseDesc,
        prepTime = this.prepTime,
        restTime = this.restTime,
        durationTime = this.durationTime,
        items = this.items,
        parentRoutineId = parentRoutineId
    )
}
