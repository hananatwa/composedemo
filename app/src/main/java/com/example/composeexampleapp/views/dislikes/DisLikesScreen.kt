package com.example.composeexampleapp.views.dislikes

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.composeexampleapp.R
import com.example.composeexampleapp.viewModel.SuperHeroesViewModel
import com.example.composeexampleapp.views.HeroesScreen
import com.example.composeexampleapp.views.likes.LoadingView

@Composable
fun DisLikesScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {

    val disLikedSuperHeroesState = viewModel.disLikedSuperHeroes

    LaunchedEffect(Unit) {
        viewModel.getDisLikedSuperHeroesFromDataBase()
    }

    if (disLikedSuperHeroesState.loading) {
        LoadingView()
    } else {
        HeroesScreen(
            navController = navController,
            heroes = disLikedSuperHeroesState.heroes,
            emptyMessage = "No Disliked superheroes",
            emptyImageRes = R.drawable.thumb_down,
            onLongPress = { hero ->
                hero.isDisLiked = false
                viewModel.updateDisliked(hero)
            }
        )
    }
}