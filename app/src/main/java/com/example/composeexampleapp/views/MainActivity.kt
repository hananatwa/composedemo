package com.example.composeexampleapp.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation2()

        }
    }
}

/*@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "heroes") {
        composable("heroes") {
            SuperHeroesListScreen(onHeroClick = { id ->
                navController.navigate("heroes/$id")
            })
        }
        composable("heroes/{hero_id}", arguments = listOf(navArgument("hero_id") {
            type = NavType.IntType
        })) {
            SuperHeroDetailsScreen()
        }
    }
}*/


@Composable
fun AppNavigation2() {
    val mainNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(mainNavController) }
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { SuperHeroesListScreen(mainNavController) }
            composable(Screen.Likes.route) { LikesScreen(mainNavController) }
            composable(Screen.DisLikes.route) { DisLikesScreen(mainNavController) }
            composable(
                route = Screen.HomeDetails.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                SuperHeroDetailsScreen(
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.Likes, Screen.DisLikes)
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { /* Add icon composable here if needed */ },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
