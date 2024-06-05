package com.example.composeexampleapp.views.likes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composeexampleapp.R
import com.example.composeexampleapp.viewModel.SuperHeroesViewModel
import com.example.composeexampleapp.views.HeroesScreen

@Composable
fun LikesScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {


    val state = viewModel.likedSuperHeroes

    LaunchedEffect(Unit) {
        viewModel.getLikedSuperHeroesFromDataBase()
    }

    if (state.loading){
        LoadingView()
    }
    else{
    HeroesScreen(
        navController = navController,
        heroes = state.heroes,
        emptyMessage = "No Liked superheroes",
        emptyImageRes = R.drawable.thumb_up,
        onLongPress = { hero ->
            hero.isLiked = false
            viewModel.updateLiked(hero)
            //viewModel.getLikedSuperHeroesFromDataBase()

        }

    )
    }
}

@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

