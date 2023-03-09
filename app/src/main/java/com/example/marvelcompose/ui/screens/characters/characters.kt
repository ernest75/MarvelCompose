package com.example.marvelcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.ui.screens.characters.CharacterDetailViewModel
import com.example.marvelcompose.ui.screens.characters.CharactersViewModel
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit, viewModel: CharactersViewModel = viewModel()) {
    MarvelItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel(), onUpClick: () -> Unit) {
        MarvelItemDetailScreen(
            loading = viewModel.state.loading,
            marvelItem = viewModel.state.character,
            onUpClick
        )

}