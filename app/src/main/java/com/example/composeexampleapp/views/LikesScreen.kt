package com.example.composeexampleapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun LikesScreen(navController: NavHostController) {
    Box {
        Text(text = "Likes Screen")
    }
}