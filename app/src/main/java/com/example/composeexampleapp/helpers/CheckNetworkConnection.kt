package com.example.composeexampleapp.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import com.example.composeexampleapp.views.SuperHeroesApplication

class CheckNetworkConnection {


     fun isNetworkAvailable(): Boolean {
        val connectivityManager = SuperHeroesApplication.getApplicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}