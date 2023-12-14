package com.example.marvelcompose.ui.screens.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import arrow.core.Either
import arrow.core.right
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(stateSavedStateHandle: SavedStateHandle, repository: CharactersRepository): ViewModel() {

    private val id = stateSavedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(character = repository.find(id))
        }
    }

    data class UiState(
        var loading: Boolean = false,
        var character: Result<Character?> = Either.Right(null)
    )
}