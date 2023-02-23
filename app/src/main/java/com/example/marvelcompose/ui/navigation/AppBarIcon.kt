package com.example.marvelcompose.ui.screens.characterdetail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppBarIcon(imageVector: ImageVector, onClick: () -> Unit, contentDescription: String) {
    IconButton(onClick = onClick ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}