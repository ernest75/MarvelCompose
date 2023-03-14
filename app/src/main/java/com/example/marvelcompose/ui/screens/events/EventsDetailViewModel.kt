package com.example.marvelcompose.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.repositories.EventsRepository
import com.example.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class EventsDetailViewModel(stateSavedStateHandle: SavedStateHandle): ViewModel() {

    private val id = stateSavedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    var state by mutableStateOf(UiState())
    private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(event = EventsRepository.find(id))
        }
    }

    data class UiState(
        var loading: Boolean = false,
        var event: Event? = null
    )
}