package com.example.dailystretch.di

import android.content.Context
import com.example.dailystretch.data.DailyStretchDatabase
import com.example.dailystretch.data.dao.RoutineDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DailyStretchDatabase {
        return DailyStretchDatabase.getDatabase(context)
    }

    @Provides
    fun provideUserDao(database: DailyStretchDatabase): RoutineDao {
        return database.routineDao()
    }
}
