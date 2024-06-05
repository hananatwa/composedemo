package com.example.composeexampleapp.views

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.composeexampleapp.R
import com.example.composeexampleapp.viewModel.SuperHeroesViewModel

@Composable
fun LikesScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getLikedSuperHeroesFromDataBase()
    }

    val likedSuperHeroes by viewModel.likedSuperHeroes.observeAsState(emptyList())

    HeroesScreen(
        navController = navController,
        heroes = likedSuperHeroes,
        emptyMessage = "No Liked superheroes",
        emptyImageRes = R.drawable.thumb_up,
        loadData = { viewModel.getLikedSuperHeroesFromDataBase() },
        onLongPress = { hero ->
            hero.isLiked = false
            viewModel.updateLiked(hero)
            viewModel.getLikedSuperHeroesFromDataBase()

        }

    )
}

