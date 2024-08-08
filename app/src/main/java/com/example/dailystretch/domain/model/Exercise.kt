package com.example.dailystretch.domain.model

data class Exercise(
    val id: String? = null,
    val exerciseName: String,
    val exerciseDesc: String?,
    val items: String?,
    val prepTime: String,
    val restTime: String
)
