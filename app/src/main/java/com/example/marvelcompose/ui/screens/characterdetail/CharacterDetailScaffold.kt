package com.example.marvelcompose.ui.screens.characterdetail

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.marvelcompose.R
import com.example.marvelcompose.data.entities.Character

@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = character.name) },
                navigationIcon = {
                    IconButton(onUpClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back arrow"
                        )
                    }
                },
                actions = {
                    AppBarOverFlowMenu(urls = character.urls)
                }
            )
        },
        floatingActionButton = {
            if(character.urls.isNotEmpty()) {
                FloatingActionButton(onClick = { shareCharacter(context, character) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.share_character)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = content
    )
}

fun shareCharacter(context: Context, character: Character) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(character.name)
        .setText(character.urls.first().url)
        .intent.also(context::startActivity)
}
