package com.example.marvelcompose.ui.screens.creators

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.repositories.CreatorsRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CreatorsScreen(onClick: (Creator) -> Unit, viewModel: CreatorsViewModel = viewModel()) {
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
fun CreatorDetailScreen(viewModel: CreatorsDetailViewModel = viewModel(), onUpClick: () -> Unit) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.creator,
        onUpClick
    )
}