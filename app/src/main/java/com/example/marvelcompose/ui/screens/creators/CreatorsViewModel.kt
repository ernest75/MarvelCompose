package com.example.marvelcompose.ui.screens.creators

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.repositories.CreatorsRepository
import kotlinx.coroutines.launch

class CreatorsViewModel: ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(items = CreatorsRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Creator> = emptyList()
    )
}