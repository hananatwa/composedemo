package com.example.composeexampleapp.model.dataclasses

import com.example.composeexampleapp.model.dataclasses.SuperHero

data class HeroesScreenState(val heroes: List<SuperHero>,
                             val loading: Boolean,
                             val error: String? = null)

data class HeroScreenState(val hero: SuperHero?=null,
                           val loading: Boolean,
                           val error: String? = null)

