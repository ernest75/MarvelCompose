package com.example.marvelcompose.ui.screens.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.MarvelItem
import com.example.marvelcompose.data.entities.Result
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    loading: Boolean = false,
    items: Result<List<T>>,
    onClick: (T) -> Unit
) {
    items.fold({ ErrorMessage(it) }) { marvelItems ->
        var bottomSheetItem by remember { mutableStateOf<T?>(null) }
        val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        BackHandler(sheetState.isVisible) {
            scope.launch { sheetState.hide() }
        }

        ModalBottomSheetLayout(
            sheetContent = {
                MarvelItemBottomPreview(
                    item = bottomSheetItem,
                    onGoToDetail = {
                        scope.launch {
                            sheetState.hide()
                            onClick(it)
                        }
                    }
                )
            },
            sheetState = sheetState
        ) {
            MarvelItemsList(
                loading = loading,
                items = marvelItems,
                onClick = onClick,
                onItemMore = {
                    bottomSheetItem = it
                    scope.launch { sheetState.show() }
                }
            )
        }
    }

}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsList(
    loading: Boolean,
    items: List<T>,
    onClick: (T) -> Unit,
    onItemMore: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        }
        if (items.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(items) {
                    MarvelListItem(
                        marvelItem = it,
                        modifier = Modifier.clickable { onClick(it) },
                        onItemMore = onItemMore
                    )
                }
            }
        }
    }
}