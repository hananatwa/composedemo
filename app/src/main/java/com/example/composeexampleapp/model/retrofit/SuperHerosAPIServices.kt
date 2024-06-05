package com.example.composeexampleapp.model.retrofit

import com.example.composeexampleapp.model.dataclasses.MarvelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SuperHeroesAPIServices {
    @GET("characters")
    suspend fun getSuperHeroes(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<MarvelData>

    @GET("characters/{characterId}")
    suspend fun getSuperHeroById(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): MarvelData
}