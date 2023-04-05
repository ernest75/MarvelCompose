package com.example.marvelcompose.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.MarvelItem
import com.example.marvelcompose.ui.MarvelScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun <T : MarvelItem> MarvelItemBottomPreview(item: T?, onGoToDetail: (T) -> Unit) {
    item?.let {
        Row(
            modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.thumbnail),
                contentDescription = item.title,
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1 / 1.5f)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.h6)
                Text(text = item.description)
                Button(
                    onClick = { onGoToDetail(item) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(id = com.example.marvelcompose.R.string.go_to_detail))
                }
            }
        }
    }.run {
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Preview
@Composable
fun MarvelItemBottomPreviewPrev() {
    MarvelScreen {
        MarvelItemBottomPreview(
            item = Character(
                id = 1,
                title = "title",
                description = "descriptiondsfwvwvwvwvwvwvwv",
                thumbnail = "",
                references = emptyList(),
                urls = emptyList()
            ),
            onGoToDetail = {}
        )
    }
}
