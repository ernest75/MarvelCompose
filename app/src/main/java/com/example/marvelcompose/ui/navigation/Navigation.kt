package com.example.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.ui.screens.*


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.charactersNav(navController: NavHostController) {

    navigation(
        startDestination = NavItem.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavItem.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = { character ->
                navController.navigate(
                    NavItem.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id)
                )
            })
        }

        composable(NavItem.ContentTypeDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                characterId = it.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.comicsNav(navController: NavHostController) {

    navigation(
        startDestination = NavItem.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavItem.ContentType(Feature.COMICS)) {
            ComicsScreen(onClick = { comic ->
                navController.navigate(
                    NavItem.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                )
            })
        }

        composable(NavItem.ContentTypeDetail(Feature.COMICS)) {
            ComicDetailScreen(
                comicId = it.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
private fun NavGraphBuilder.eventsNav(navController: NavHostController) {

    navigation(
        startDestination = NavItem.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavItem.ContentType(Feature.EVENTS)) {
            EventsScreen(onClick = { event ->
                navController.navigate(
                    NavItem.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                )
            })
        }

        composable(NavItem.ContentTypeDetail(Feature.EVENTS)) {
            EventDetailScreen(
                eventId = it.findArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
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