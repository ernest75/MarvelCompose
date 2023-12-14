package com.example.marvelcompose.ui.screens.creators

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.CreatorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatorsViewModel @Inject constructor(repository: CreatorsRepository): ViewModel() {
    private val _state =  MutableStateFlow(UiState())
    val state  =_state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = repository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Creator>> = emptyList<Creator>().right()
    )
}