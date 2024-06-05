package com.example.composeexampleapp.views.home

import android.widget.Toast
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.example.composeexampleapp.viewModel.SuperHeroesViewModel
import com.example.composeexampleapp.model.dataclasses.Screen
import com.example.composeexampleapp.views.SuperHeroesApplication


@Composable
fun SuperHeroesListScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {
    val state = viewModel.heroesScreenState
    val context = SuperHeroesApplication.getApplicationContext()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.heroes) { hero ->
            SwipeableHeroItem(
                hero,
                onSwipeLeft = {
                    hero.isLiked = true
                    hero.isDisLiked = false
                    viewModel.updateLiked(hero)
                    Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show()
                },
                onSwipeRight = {
                    hero.isDisLiked = true
                    hero.isLiked = false
                    viewModel.updateDisliked(hero)
                    Toast.makeText(context, "Dis Liked", Toast.LENGTH_SHORT).show()

                }) {
                hero.id?.let { id ->
                    run {
                        navController.navigate(
                            Screen.HomeDetails.createRoute(
                                id
                            )
                        )
                    }
                }
            }
        }
    }
}
