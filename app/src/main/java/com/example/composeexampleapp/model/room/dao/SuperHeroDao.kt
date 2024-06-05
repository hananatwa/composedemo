package com.example.composeexampleapp.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composeexampleapp.model.dataclasses.SuperHero

@Dao
interface SuperHeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuperHeroes(superHeroes: List<SuperHero>)

    @Query("SELECT * FROM superhero_database")
    suspend fun getAllSuperHeroes(): List<SuperHero>

    @Query("SELECT * FROM superhero_database WHERE id = :heroId LIMIT 1")
    suspend fun getHeroDetails(heroId: Int): SuperHero

    @Update
    suspend fun updateLiked(superHero: SuperHero)

    @Update
    suspend fun updateDisliked(superHero: SuperHero)
}