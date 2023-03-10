package com.example.marvelcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by remember() { mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }
    MarvelItemsListScreen(
        items = charactersState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(characterId: Int, onUpClick: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }
    characterState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}