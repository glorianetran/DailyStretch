package com.example.dailystretch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dailystretch.data.repository.RoutineRepository
import com.example.dailystretch.domain.model.RoutineUiModel
import com.example.dailystretch.utils.toMinutesAndSecondsString
import kotlin.math.min


@Entity(tableName = ROUTINE_TABLE)
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true) val routineId: Long = 0,
    val name: String,
    val time: String
)

const val ROUTINE_TABLE = "routines"

fun RoutineEntity.toRoutineUiModel(): RoutineUiModel {
    return RoutineUiModel(
        id = this.routineId,
        name = this.name,
        minutesAndSeconds = this.time.toInt().toMinutesAndSecondsString()
    )
}
