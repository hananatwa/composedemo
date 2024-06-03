package com.example.composeexampleapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexampleapp.helpers.Constants
import com.example.composeexampleapp.reposatory.daos.SuperHero
import com.example.composeexampleapp.reposatory.retrofit.RetrofitBuilder
import kotlinx.coroutines.launch

class SuperHeroDetailsViewModel(private val savedStateHandle :SavedStateHandle): ViewModel(){

    private val _character = MutableLiveData<SuperHero?>(null)
    val character: LiveData<SuperHero?> get() = _character


    init {
        val heroId = savedStateHandle.get<Int>("id")
        if (heroId != null) {
            fetchCharacterById(heroId)
        }
    }
    private fun fetchCharacterById(characterId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.instance.getSuperHeroById(
                    characterId = characterId,
                    ts = Constants.TS,
                    apiKey = Constants.API_KEY,
                    hash = Constants.HASH_KEY
                )
                _character.value = response.data?.results?.firstOrNull()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}