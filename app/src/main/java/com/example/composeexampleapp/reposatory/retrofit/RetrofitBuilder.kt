package com.example.composeexampleapp.reposatory.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitBuilder {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    val instance: SuperHeroesAPIServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SuperHeroesAPIServices::class.java)
    }
}