package com.example.composeexampleapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexampleapp.helpers.CheckNetworkConnection
import com.example.composeexampleapp.model.repository.HeroesRepository
import com.example.composeexampleapp.model.dataclasses.SuperHero
import com.example.composeexampleapp.model.dataclasses.HeroesScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SuperHeroesViewModel : ViewModel() {

    var heroesScreenState by mutableStateOf(
        HeroesScreenState(
            heroes = emptyList(),
            loading = true
        )
    )
        private set

    var likedSuperHeroes by mutableStateOf(HeroesScreenState(heroes = emptyList(), loading = true))
        private set

    var disLikedSuperHeroes by mutableStateOf(
        HeroesScreenState(
            heroes = emptyList(),
            loading = true
        )
    )
        private set

    private val heroesRepository = HeroesRepository()

    init {
        getSuperHeroes()
    }

    private fun getSuperHeroes() {
        viewModelScope.launch {
            heroesScreenState = heroesScreenState.copy(loading = true)

            withContext(Dispatchers.IO) {
                if (heroesRepository.getSuperHeroesFromRoom().isEmpty()) {

                    if (CheckNetworkConnection().isNetworkAvailable()) {
                        try {
                            heroesRepository.getSuperHeroesFromAPIAndInsertIntoRoom()
                            heroesScreenState = heroesScreenState.copy(loading = false)

                        } catch (error: Error) {
                            heroesScreenState =
                                heroesScreenState.copy(error = error.message.toString())

                        }
                    }
                } else {
                    heroesScreenState =
                        heroesScreenState.copy(error = "Connect to the network and try again please")

                }

            }
            heroesScreenState =
                heroesScreenState.copy(heroes = heroesRepository.getSuperHeroesFromRoom())
        }
    }


    fun updateLiked(superHero: SuperHero) {
        viewModelScope.launch(Dispatchers.IO) {
            heroesRepository.updateLiked(superHero)
            getLikedSuperHeroesFromDataBase()
        }
    }

    fun updateDisliked(superHero: SuperHero) {
        viewModelScope.launch(Dispatchers.IO) {
            heroesRepository.updateDisLiked(superHero)
            getDisLikedSuperHeroesFromDataBase()
        }
    }


    fun getLikedSuperHeroesFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val likedHeroes =
                heroesRepository.getSuperHeroesFromRoom()
                    .filter { superHero -> superHero.isLiked == true }
            likedSuperHeroes = likedSuperHeroes.copy(heroes = likedHeroes, loading = false)
        }
    }

    fun getDisLikedSuperHeroesFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val disLikedHeroes =
                heroesRepository.getSuperHeroesFromRoom()
                    .filter { superHero -> superHero.isDisLiked == true }
            disLikedSuperHeroes = disLikedSuperHeroes.copy(heroes = disLikedHeroes, loading = false)

        }
    }


}