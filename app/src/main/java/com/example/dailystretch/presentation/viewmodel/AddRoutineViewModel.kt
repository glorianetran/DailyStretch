package com.example.dailystretch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dailystretch.domain.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddRoutineViewModel @Inject constructor(): ViewModel() {
    private val _exerciseList = MutableStateFlow<List<Exercise>>(emptyList())
    val exerciseList: StateFlow<List<Exercise>> = _exerciseList

    fun addExercise(exercise: Exercise) {
        val exerciseWithId = exercise.copy(id = UUID.randomUUID().toString())
        _exerciseList.value += exerciseWithId
        println("Items updated: ${_exerciseList.value}")
    }

    fun removeItem(exercise: Exercise) {
        _exerciseList.value = _exerciseList.value.filterNot { it.id == exercise.id }
        println("Items updated: ${_exerciseList.value}")
    }

    fun clearList() {
        _exerciseList.value = emptyList()
    }
}
