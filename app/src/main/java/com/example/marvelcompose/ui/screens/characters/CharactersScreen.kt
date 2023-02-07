package com.example.marvelcompose.ui.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.marvelcompose.R
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.ui.screens.characterdetail.AppBarOverFlowMenu

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var characterState by rememberSaveable { mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.getCharacters()
    }

    CharactersScreen(
        character = characterState,
        onClick = onClick
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(character: List<Character>, onClick: (Character) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })

    }) { padding ->
        LazyVerticalGrid(
            cells = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(character) {
                CharacterItem(
                    character = it,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Card {
            Image(
                painter = rememberImagePainter(character.thumbnail),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            AppBarOverFlowMenu(
                urls = character.urls,
                modifier = modifier.padding(start = 120.dp)
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}
