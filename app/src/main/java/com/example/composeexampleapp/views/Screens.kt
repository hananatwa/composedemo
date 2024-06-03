package com.example.composeexampleapp.views


sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("home", "Home")
    data object Likes : Screen("likes", "Likes")
    data object DisLikes : Screen("dis_likes", "DisLikes")
    data object HomeDetails : Screen("home_details/{id}", "Home Details") {
        fun createRoute(id: Int) = "home_details/$id"
    }
}