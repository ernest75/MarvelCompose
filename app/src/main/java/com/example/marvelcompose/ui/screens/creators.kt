package com.example.marvelcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.repositories.CreatorsRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CreatorsScreen(onClick: (Creator) -> Unit) {
    var creatorState by remember() { mutableStateOf(emptyList<Creator>()) }
    LaunchedEffect(Unit) {
        creatorState = CreatorsRepository.get()
    }
    MarvelItemsListScreen(
        items = creatorState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CreatorDetailScreen(creatorId: Int, onUpClick: () -> Unit) {
    var creatorState by remember { mutableStateOf<Creator?>(null) }
    LaunchedEffect(Unit) {
        creatorState = CreatorsRepository.find(creatorId)
    }
    creatorState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}