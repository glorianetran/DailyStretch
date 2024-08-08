package com.example.dailystretch.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.dailystretch.data.model.ExerciseEntity
import com.example.dailystretch.data.model.RoutineEntity
import com.example.dailystretch.data.model.RoutineWithExercisesEntity


@Dao
interface RoutineDao {
    @Insert
    suspend fun insertRoutine(routine: RoutineEntity): Long

    @Insert
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM routines")
    suspend fun getAllRoutines(): List<RoutineEntity>

    @Transaction
    @Query("SELECT * FROM routines WHERE routineId = :id")
    suspend fun getRoutineWithExercises(id: Long): RoutineWithExercisesEntity
}