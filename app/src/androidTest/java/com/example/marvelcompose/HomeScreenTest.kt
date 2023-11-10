package com.example.marvelcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.platform.app.InstrumentationRegistry
import arrow.core.Either
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.entities.ReferenceList
import com.example.marvelcompose.data.entities.Url
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val ctxt = InstrumentationRegistry.getInstrumentation().targetContext
    val items: List<Comic> =(1..100).map {
        Comic(
            id = it,
            title = "Title$it",
            description = "descr$it",
            thumbnail = "",
            format =  Comic.Format.COMIC,
            references= emptyList(),
            urls = emptyList()
        )
    }



    @Before
    fun setUp() {
        composeTestRule.setContent {
            MarvelItemsListScreen(items = Either.Right(items), onClick = {})
        }
    }

    @Test
    fun navigatesTo51(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(25)
        onNodeWithText("Title51").assertExists()
    }

    @Test
    fun navigatesTo51AndShowsBottomSheet(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(25)
        onNodeWithText("Title51").assertExists()
        onNode(
            hasParent(hasText("Title51"))
                    and hasContentDescription(ctxt.getString(R.string.more_actions))
        ).performClick()
        onNodeWithText("descr51").assertExists()
    }
}