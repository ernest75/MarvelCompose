package com.example.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.screens.characterdetail.CharacterDetailScreen
import com.example.marvelcompose.ui.screens.characters.CharactersScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.ContentType(Feature.CHARACTERS).route
    ) {
        composable(NavItem.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = { character ->
                navController.navigate(NavItem.ContentDetail(Feature.CHARACTERS).createRoute(character.id))
            })
        }

        composable(NavItem.ContentDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                id = it.findArg<Int>(NavArg.ItemId),
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