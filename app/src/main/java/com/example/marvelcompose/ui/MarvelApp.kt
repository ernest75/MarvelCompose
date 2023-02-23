package com.example.marvelcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.navigation.AppBottomNavigation
import com.example.marvelcompose.ui.navigation.Navigation
import com.example.marvelcompose.ui.navigation.navigatePoppingUpToStartDestination
import com.example.marvelcompose.ui.screens.characterdetail.AppBarIcon
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import com.example.marvelcompose.R
import com.example.marvelcompose.ui.navigation.NavItem

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()
    val showUpNavigation = currentRoute !in NavItem.values().map { it.navCommand.route }
    MarvelScreen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    navigationIcon = if (showUpNavigation) {
                        {
                            AppBarIcon(
                                imageVector = Icons.Default.ArrowBack,
                                onClick = { navController.popBackStack() },
                                contentDescription = "navigation"
                            )
                        }
                    } else null
                )
            },
            bottomBar = {
            AppBottomNavigation(
                currentRoute = currentRoute,
                onNavItemClick = { navController.navigatePoppingUpToStartDestination(it.navCommand.route) })
        }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}