package com.example.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.R
import com.example.marvelcompose.ui.screens.*


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
        creatorsNav(navController)
        composable(NavCommand.ContentType(Feature.SETTINGS)) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.settings), style = MaterialTheme.typography.h3)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.charactersNav(navController: NavHostController) {

    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = { character ->
                navController.navigate(
                    NavCommand.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id)
                )
            })
        }

        composable(NavCommand.ContentTypeDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                characterId = it.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.comicsNav(navController: NavHostController) {

    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(onClick = { comic ->
                navController.navigate(
                    NavCommand.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                )
            })
        }

        composable(NavCommand.ContentTypeDetail(Feature.COMICS)) {
            ComicDetailScreen(
                comicId = it.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.eventsNav(navController: NavHostController) {

    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(onClick = { event ->
                navController.navigate(
                    NavCommand.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                )
            })
        }

        composable(NavCommand.ContentTypeDetail(Feature.EVENTS)) {
            EventDetailScreen(
                eventId = it.findArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.creatorsNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CREATORS).route,
        route = Feature.CREATORS.route
    ) {
        composable(NavCommand.ContentType(Feature.CREATORS)) {
            CreatorsScreen(onClick = { event ->
                navController.navigate(
                    NavCommand.ContentTypeDetail(Feature.CREATORS).createRoute(event.id)
                )
            })
        }

        composable(NavCommand.ContentTypeDetail(Feature.CREATORS)) {
            CreatorDetailScreen(
                creatorId = it.findArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}