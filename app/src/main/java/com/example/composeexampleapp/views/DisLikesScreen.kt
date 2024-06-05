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
fun DisLikesScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getDisLikedSuperHeroesFromDataBase()
    }

    val disLikedSuperHeroes by viewModel.disLikedSuperHeroes.observeAsState(emptyList())

    HeroesScreen(
        navController = navController,
        heroes = disLikedSuperHeroes,
        emptyMessage = "No Disliked superheroes",
        emptyImageRes = R.drawable.thumb_down,
        loadData = { viewModel.getDisLikedSuperHeroesFromDataBase() },
        onLongPress = { hero ->
            hero.isDisLiked = false
            viewModel.updateDisliked(hero)
        }
    )
}