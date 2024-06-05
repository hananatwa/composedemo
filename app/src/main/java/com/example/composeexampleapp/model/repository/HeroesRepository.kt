package com.example.composeexampleapp.model.repository

import com.example.composeexampleapp.helpers.Constants
import com.example.composeexampleapp.model.dataclasses.SuperHero
import com.example.composeexampleapp.model.retrofit.RetrofitBuilder
import com.example.composeexampleapp.model.room.dao.SuperHeroDao
import com.example.composeexampleapp.model.room.database.SuperheroDatabase
import com.example.composeexampleapp.views.SuperHeroesApplication

class HeroesRepository {

    private val context = SuperHeroesApplication.getApplicationContext()
    private var heroesDao: SuperHeroDao = SuperheroDatabase.getDBInstance(context)

    suspend fun getSuperHeroesFromAPIAndInsertIntoRoom() {
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
    }

    suspend fun getSuperHeroesFromRoom(): List<SuperHero> {
        return heroesDao.getAllSuperHeroes()
    }

    suspend fun updateLiked(superHero: SuperHero) {
        heroesDao.updateLiked(superHero)
    }

    suspend fun updateDisLiked(superHero: SuperHero) {
        heroesDao.updateDisliked(superHero)
    }

     suspend fun getHeroDetails(heroId: Int): SuperHero {
        return heroesDao.getHeroDetails(heroId)
    }
}