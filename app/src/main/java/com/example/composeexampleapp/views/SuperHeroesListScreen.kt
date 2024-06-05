package com.example.composeexampleapp.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.example.composeexampleapp.viewModel.SuperHeroesViewModel


@Composable
fun SuperHeroesListScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {
    val characters by viewModel.characters.observeAsState(emptyList())
    val context =SuperHeroesApplication.getApplicationContext()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(characters) { hero ->
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
