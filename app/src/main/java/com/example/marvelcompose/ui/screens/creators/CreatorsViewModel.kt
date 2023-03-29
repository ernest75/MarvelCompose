package com.example.marvelcompose.ui.screens.creators

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.CreatorsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatorsViewModel: ViewModel() {
    private val _state =  MutableStateFlow(UiState())
    val state  =_state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = CreatorsRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Creator>> = emptyList<Creator>().right()
    )
}