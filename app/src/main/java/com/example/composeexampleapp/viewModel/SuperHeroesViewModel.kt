package com.example.composeexampleapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexampleapp.helpers.Constants
import com.example.composeexampleapp.reposatory.daos.SuperHero
import com.example.composeexampleapp.reposatory.retrofit.RetrofitBuilder
import kotlinx.coroutines.launch

class SuperHeroesViewModel : ViewModel() {

    private val _superHeroes = MutableLiveData<List<SuperHero>>()
    val characters: LiveData<List<SuperHero>> get() = _superHeroes

    init {
        getSuperHeroes()
    }

    private fun getSuperHeroes() {
        viewModelScope.launch {
            val response = RetrofitBuilder.instance.getSuperHeroes(
                Constants.TS,
                Constants.API_KEY,
                Constants.HASH_KEY
            )
            if (response.isSuccessful && response.body() != null) {
                _superHeroes.postValue(response.body()?.data?.results)
                Log.i("TAG1", "getSuperHeroes: " + response.body()?.data?.results)
            }
        }
    }
}