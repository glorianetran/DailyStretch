package com.example.dailystretch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailystretch.data.model.toRoutineUiModel
import com.example.dailystretch.data.repository.RoutineRepository
import com.example.dailystretch.domain.model.RoutineUiModel
import com.example.dailystretch.domain.model.RoutineWithExercisesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeRoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository
): ViewModel() {

    private val _routineList = MutableStateFlow<List<RoutineUiModel>>(emptyList())
    val routineList: StateFlow<List<RoutineUiModel>> = _routineList

    private val _routineWithExercise = MutableStateFlow(RoutineWithExercisesUiModel())
    val routineWithExercise: StateFlow<RoutineWithExercisesUiModel> = _routineWithExercise

    fun getAllRoutines() {
        viewModelScope.launch {
            val routineList = routineRepository.getAllRoutines()
            _routineList.value = routineList.map { it.toRoutineUiModel() }
        }
    }

    fun getRoutineWithExercises(id: Long) {
        viewModelScope.launch {
            val routineAndExercises = routineRepository.getRoutineWithExercises(id)
            _routineWithExercise.value = routineAndExercises
        }
    }

    fun deleteRoutine(id: Long) {
        viewModelScope.launch {
            routineRepository.deleteRoutineById(id)
        }
    }
}
