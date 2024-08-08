package com.example.dailystretch.di

import com.example.dailystretch.data.repository.RoutineRepository
import com.example.dailystretch.data.repository.RoutineRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRoutineRepository(
    ): RoutineRepository {
        return RoutineRepositoryImpl()
    }
}
