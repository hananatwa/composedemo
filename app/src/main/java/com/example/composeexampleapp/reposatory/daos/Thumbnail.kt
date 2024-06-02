package com.example.composeexampleapp.reposatory.daos

import com.google.gson.annotations.SerializedName


data class Thumbnail(

    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null

)