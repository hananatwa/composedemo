package com.example.composeexampleapp.reposatory.retrofit

import com.example.composeexampleapp.reposatory.daos.MarvelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SuperHeroesAPIServices {
    @GET("characters")
    suspend fun getSuperHeroes(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<MarvelData>
}