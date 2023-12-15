package com.example.marvelcompose.ui.screens.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        loading = state.loading,
        items = state.items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(viewModel: EventsDetailViewModel = hiltViewModel(), onUpClick: () -> Unit) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.event,
        onUpClick = onUpClick
    )
}