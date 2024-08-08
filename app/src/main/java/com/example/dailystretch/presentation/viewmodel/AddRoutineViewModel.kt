package com.example.dailystretch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailystretch.data.repository.RoutineRepository
import com.example.dailystretch.domain.model.ExerciseUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddRoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,

): ViewModel() {
    private val _exerciseUiModelList = MutableStateFlow<List<ExerciseUiModel>>(emptyList())
    val exerciseUiModelList: StateFlow<List<ExerciseUiModel>> = _exerciseUiModelList

    fun addExercise(exerciseUiModel: ExerciseUiModel) {
        val exerciseWithId = exerciseUiModel.copy(id = UUID.randomUUID().toString())
        _exerciseUiModelList.value += exerciseWithId
        println("Items updated: ${_exerciseUiModelList.value}")
    }

    fun removeItem(exerciseUiModel: ExerciseUiModel) {
        _exerciseUiModelList.value = _exerciseUiModelList.value.filterNot { it.id == exerciseUiModel.id }
        println("Items updated: ${_exerciseUiModelList.value}")
    }

    fun clearList() {
        _exerciseUiModelList.value = emptyList()
    }

    fun addRoutine(
        routineName: String,
        exerciseUiModelList: List<ExerciseUiModel>
    ) {
        viewModelScope.launch {
            routineRepository.addRoutine(routineName, exerciseUiModelList)
        }
    }
}
