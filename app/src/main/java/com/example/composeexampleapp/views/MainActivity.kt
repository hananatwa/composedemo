package com.example.composeexampleapp.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.material.*
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()

        }
    }
}


@Composable
fun AppNavigation() {
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
