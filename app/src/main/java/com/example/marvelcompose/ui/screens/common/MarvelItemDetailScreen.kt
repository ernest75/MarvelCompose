package com.example.marvelcompose.ui.screens.common

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.marvelcompose.R
import com.example.marvelcompose.data.entities.MarvelItem
import com.example.marvelcompose.data.entities.Reference
import com.example.marvelcompose.data.entities.ReferenceList
import com.example.marvelcompose.data.entities.Result


@ExperimentalCoilApi
@Composable
fun MarvelItemDetailScreen(
    loading: Boolean = false,
    marvelItem: Result<MarvelItem?>,
    onUpClick: () -> Unit
) {
    if (loading) {
        CircularProgressIndicator()
    }
    marvelItem.fold({ ErrorMessage(it) }) { item ->
        if (item != null) {
            MarvelItemDetailScaffold(
                marvelItem = item
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                ) {
                    item {
                        Header(marvelItem = item)
                    }
                    item.references.forEach {
                        val (icon, @StringRes stringRes) = it.type.createUiData()
                        section(icon, stringRes, it.references)
                    }
                }
            }
        }
    }

}

@ExperimentalCoilApi
@Composable
private fun Header(marvelItem: MarvelItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = marvelItem.thumbnail,
            contentDescription = marvelItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = marvelItem.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = marvelItem.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun LazyListScope.section(icon: ImageVector, @StringRes name: Int, items: List<Reference>) {
    if (items.isEmpty()) return

    item {
        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            leadingContent = { Icon(icon, contentDescription = null) },
            headlineContent = {  Text(it.name) }
        )
    }
}

private fun ReferenceList.Type.createUiData(): Pair<ImageVector, Int> = when (this) {
    ReferenceList.Type.CHARACTER -> Icons.Default.Person to R.string.characters
    ReferenceList.Type.COMIC -> Icons.Default.Book to R.string.comics
    ReferenceList.Type.STORY -> Icons.Default.Bookmark to R.string.stories
    ReferenceList.Type.EVENT -> Icons.Default.Event to R.string.events
    ReferenceList.Type.SERIES -> Icons.Default.Collections to R.string.series
}

