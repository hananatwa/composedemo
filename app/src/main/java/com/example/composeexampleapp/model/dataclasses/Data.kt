package com.example.composeexampleapp.model.dataclasses

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("results") var results: ArrayList<SuperHero> = arrayListOf()

)