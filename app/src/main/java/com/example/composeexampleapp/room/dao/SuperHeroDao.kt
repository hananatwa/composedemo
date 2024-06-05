package com.example.composeexampleapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composeexampleapp.reposatory.dataclasses.SuperHero

@Dao
interface SuperHeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHero(superHero: SuperHero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuperHeroes(superHeroes: List<SuperHero>)

    @Query("SELECT * FROM superhero_database")
    suspend fun getAllSuperHeroes(): List<SuperHero>

    @Delete
    fun deleteSuperHero(superHero: SuperHero)

    @Update
    suspend fun updateLiked(superHero: SuperHero)

    @Update
    suspend fun updateDisliked(superHero: SuperHero)
}