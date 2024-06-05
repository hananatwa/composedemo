package com.example.composeexampleapp.viewModel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexampleapp.model.repository.HeroesRepository
import com.example.composeexampleapp.model.dataclasses.HeroScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SuperHeroDetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


    private val _heroesDetailsScreenState = MutableStateFlow(HeroScreenState(loading = true))
    val heroesDetailsScreenState: StateFlow<HeroScreenState> = _heroesDetailsScreenState
    private val heroesRepository = HeroesRepository()


    init {
        fetchSuperHero()
    }

    fun fetchSuperHero(){
        val heroId = savedStateHandle.get<Int>("id")
        if (heroId != null) {
            fetchSuperHeroById(heroId)
        }
    }

    private fun fetchSuperHeroById(heroId: Int) {
        viewModelScope.launch {
            try {
                val hero = heroesRepository.getHeroDetails(heroId)
                _heroesDetailsScreenState.value = HeroScreenState(hero = hero, loading = false)
            } catch (e: Exception) {
                _heroesDetailsScreenState.value = HeroScreenState(loading = false, error = e.message)
            }
        }
    }
}