package com.example.composeexampleapp.views

import android.app.Application
import android.content.Context

class SuperHeroesApplication:Application() {
    init {
        application = this
    }
    companion object{
        private lateinit var application: SuperHeroesApplication
        fun getApplicationContext():Context = application.applicationContext
    }
}