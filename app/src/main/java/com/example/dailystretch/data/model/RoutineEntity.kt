package com.example.dailystretch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = ROUTINE_TABLE)
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)

const val ROUTINE_TABLE = "routines"