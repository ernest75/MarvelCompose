package com.example.marvelcompose.ui.screens.creators

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.CreatorsRepository
import com.example.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatorsDetailViewModel @Inject constructor(stateSavedStateHandle: SavedStateHandle, repository: CreatorsRepository): ViewModel() {

    private val id = stateSavedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(creator = repository.find(id))
        }
    }

    data class UiState(
        var loading: Boolean = false,
        var creator: Result<Creator?> = Either.Right(null)
    )

}