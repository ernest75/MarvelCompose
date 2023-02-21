package com.example.marvelcompose.ui.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.repositories.ComicsRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemsList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember() { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    val formats = Comic.Format.values().take(3)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            formats.forEach {
                Tab(
                    selected = it.ordinal == pagerState.currentPage,
                    onClick = { scope.launch { pagerState.scrollToPage(it.ordinal) } },
                    text = { Text(text = it.name) }
                )

            }
        }
        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) {
            MarvelItemsList(
                items = comicsState,
                onClick = onClick
            )
        }
    }
    

}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(comicId: Int, onUpClick: () -> Unit) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(comicId)
    }
    comicState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}