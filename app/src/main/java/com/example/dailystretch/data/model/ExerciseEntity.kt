package com.example.dailystretch.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = EXERCISE_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = RoutineEntity::class,
            parentColumns = ["routineId"],
            childColumns = ["parentRoutineId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0,
    val parentRoutineId: Long,
    val name: String,
    val description: String?,
    val items: String?,
    val prepTime: String,
    val restTime: String,
    val durationTime: String
)

const val EXERCISE_TABLE = "exercises"
