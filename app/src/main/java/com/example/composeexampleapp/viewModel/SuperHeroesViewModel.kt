package com.example.composeexampleapp.viewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexampleapp.helpers.Constants
import com.example.composeexampleapp.reposatory.dataclasses.SuperHero
import com.example.composeexampleapp.reposatory.retrofit.RetrofitBuilder
import com.example.composeexampleapp.room.dao.SuperHeroDao
import com.example.composeexampleapp.room.database.SuperheroDatabase
import com.example.composeexampleapp.views.SuperHeroesApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SuperHeroesViewModel : ViewModel() {

    private val _superHeroes = MutableLiveData<List<SuperHero>>()
    val characters: LiveData<List<SuperHero>> get() = _superHeroes

    private val _likedSuperHeroes = MutableLiveData<List<SuperHero>>()
    val likedSuperHeroes: LiveData<List<SuperHero>> get() = _likedSuperHeroes

    private val _disLikedSuperHeroes = MutableLiveData<List<SuperHero>>()
    val disLikedSuperHeroes: LiveData<List<SuperHero>> get() = _disLikedSuperHeroes

    private val context = SuperHeroesApplication.getApplicationContext()
    private var heroesDao: SuperHeroDao = SuperheroDatabase.getDBInstance(context)

    var errorMessage: MutableLiveData<String> = MutableLiveData("")

    init {
        getSuperHeroes()
    }

    private fun getSuperHeroes() {
        viewModelScope.launch {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            withContext(Dispatchers.IO) {
                if (heroesDao.getAllSuperHeroes().isEmpty()) {
                    if (activeNetwork != null) {
                        if (activeNetwork.isConnectedOrConnecting)
                            try {
                                val response = RetrofitBuilder.instance.getSuperHeroes(
                                    Constants.TS,
                                    Constants.API_KEY,
                                    Constants.HASH_KEY
                                )
                                if (response.isSuccessful && response.body() != null) {
                                    val myResponse = response.body()?.data?.results
                                    if (myResponse != null) {
                                        heroesDao.insertAllSuperHeroes(myResponse)
                                    }
                                }
                            } catch (error: Error) {
                                errorMessage.value = error.message.toString()

                            }
                    } else {
                        errorMessage.value = "Connect to the network and try again please"
                    }
                }
            }
            _superHeroes.postValue(heroesDao.getAllSuperHeroes())
        }

    }

    fun updateLiked(superHero: SuperHero) {
        viewModelScope.launch(Dispatchers.IO) {
            heroesDao.updateLiked(superHero)
            getLikedSuperHeroesFromDataBase()
        }
    }

    fun updateDisliked(superHero: SuperHero) {
        viewModelScope.launch(Dispatchers.IO) {
            heroesDao.updateDisliked(superHero)
            getDisLikedSuperHeroesFromDataBase()
        }
    }

    fun getLikedSuperHeroesFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val likedHeroes =
                heroesDao.getAllSuperHeroes().filter { superHero -> superHero.isLiked == true }
            _likedSuperHeroes.postValue(likedHeroes)
        }
    }

    fun getDisLikedSuperHeroesFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val disLikedHeroes =
                heroesDao.getAllSuperHeroes().filter { superHero -> superHero.isDisLiked == true }
            _disLikedSuperHeroes.postValue(disLikedHeroes)
        }
    }

}