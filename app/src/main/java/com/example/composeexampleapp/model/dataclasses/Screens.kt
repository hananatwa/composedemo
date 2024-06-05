package com.example.composeexampleapp.model.dataclasses

import com.example.composeexampleapp.R


sealed class Screen(val route: String, val title: String, val icon: Int? =null) {
    data object Home : Screen("home", "Home",R.drawable.home)
    data object Likes : Screen("likes", "Likes", R.drawable.thumb_up)
    data object DisLikes : Screen("dis_likes", "DisLikes", R.drawable.thumb_down)
    data object HomeDetails : Screen("home_details/{id}", "Home Details") {
        fun createRoute(id: Int) = "home_details/$id"
    }
}