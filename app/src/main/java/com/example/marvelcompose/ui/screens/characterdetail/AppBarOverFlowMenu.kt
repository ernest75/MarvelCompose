package com.example.marvelcompose.ui.screens.characterdetail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Url
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AppBarOverFlowMenu(
    urls: List<Url>,
    modifier: Modifier = Modifier
) {
    var showMenu by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    IconButton(onClick = { showMenu = !showMenu }, modifier = modifier) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More actions", tint = Color.White)
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            urls.forEach {
                DropdownMenuItem(onClick = {
                    uriHandler.openUri(it.url)
                    showMenu = false
                }) {
                    ListItem(text = { Text(it.type) })
                }
            }
        }
    }
}