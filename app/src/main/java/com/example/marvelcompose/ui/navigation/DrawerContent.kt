package com.example.marvelcompose.ui.navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.ui.MarvelScreen

@Composable
fun DrawerContent(
    drawerOptions: List<NavItem>,
    selectedIndex: Int,
    onOptionClick: (NavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.secondary
                    )
                )
            )
            .height(200.dp)
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    drawerOptions.forEachIndexed { index, navItem ->
        val selected = selectedIndex == index
        val colors = MaterialTheme.colors
        val localContentColor = if (selected) colors.primary else colors.onBackground
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            LocalContentColor provides localContentColor,
        ) {
            Row(modifier = Modifier
                .clickable { onOptionClick(navItem) }
                .fillMaxWidth()
                .padding(8.dp, 4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = if (selected) LocalContentColor.current.copy(alpha = 0.12f) else colors.surface)
                .padding(12.dp)
            ) {
                val contentAlpha = if (selected) ContentAlpha.high else ContentAlpha.medium
                CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = stringResource(id = navItem.title)
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = stringResource(id = navItem.title)
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DrawerContentPreview() {
    MarvelScreen {
        Column {
            DrawerContent(
                drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS),
                selectedIndex = 0,
                onOptionClick = {}
            )
        }
    }
}