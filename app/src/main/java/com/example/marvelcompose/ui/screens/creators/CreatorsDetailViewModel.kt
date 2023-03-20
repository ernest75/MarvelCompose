package com.example.marvelcompose.ui.screens.creators

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.data.repositories.CreatorsRepository
import com.example.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatorsDetailViewModel(stateSavedStateHandle: SavedStateHandle): ViewModel() {

    private val id = stateSavedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(creator = CreatorsRepository.find(id))
        }
    }

    data class UiState(
        var loading: Boolean = false,
        var creator: Creator? = null
    )

}