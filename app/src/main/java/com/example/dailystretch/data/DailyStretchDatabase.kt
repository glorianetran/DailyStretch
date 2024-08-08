package com.example.dailystretch.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.dailystretch.data.dao.RoutineDao
import com.example.dailystretch.data.model.ExerciseEntity
import com.example.dailystretch.data.model.RoutineEntity

@Database(
    entities = [
        ExerciseEntity::class,
        RoutineEntity::class
    ],
    version = 1
)
abstract class DailyStretchDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao

    companion object {
        @Volatile
        private var INSTANCE: DailyStretchDatabase? = null

        fun getDatabase(context: Context): DailyStretchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DailyStretchDatabase::class.java,
                    "daily_stretch_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

const val DATABASE_NAME = "daily_stretch_database"