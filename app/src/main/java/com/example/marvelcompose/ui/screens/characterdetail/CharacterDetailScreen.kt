package com.example.marvelcompose.ui.screens.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcompose.MarvelApp
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Reference

@Composable
fun CharacterDetailScreen(id: Int, onUpClick: () -> Unit) {
    var characterState by remember {
        mutableStateOf<Character?>(
            null
        )
    }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.finCharacter(id)
    }
    characterState?.let { CharacterDetailScreen(character = it, onUpClick) }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterDetailScreen(character: Character, onUpClick: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = character.name) },
            navigationIcon = {
                IconButton(onUpClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow")
                }
            }
        )

    }) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            item {
                Header(character)
            }
            section(Icons.Default.Collections, "Series", character.series)
            section(Icons.Default.Event, "Events", character.comics)
            section(Icons.Default.Book, "Comics", character.comics)
            section(Icons.Default.Bookmark, "Stories", character.comics)
        }
    }
}


fun LazyListScope.section(icon: ImageVector, title: String, listItems: List<Reference>) {
    if (listItems.isEmpty()) return
    item {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }

    items(listItems) {
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 24.dp)
            )
            Text(it.name)
        }
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
    }
}

@Composable
fun Header(character: Character) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(data = character.thumbnail),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
    }
}

@Preview
@Composable
fun CharacterDetailScreenPreview() {
    val character = Character(
        1,
        "Iron",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id auctor lectus, ut efficitur sem. Mauris dapibus nisi nec ligula pellentesque ultricies. Sed id sapien sit amet justo congue tristique. Phasellus id ornare lacus, non hendrerit mi. Proin eu nisi libero. Mauris fermentum nec sem bibendum malesuada. Aenean non tellus eros. Morbi tristique auctor arcu, vel lobortis magna pharetra eu. Proin ante purus, bibendum eget mollis ac, porta vel sapien.",
        "",
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2"))
    )
    MarvelApp {
        CharacterDetailScreen(character = character, onUpClick = {})
    }
}