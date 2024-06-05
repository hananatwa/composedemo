package com.example.composeexampleapp.model.dataclasses

import com.google.gson.annotations.SerializedName


data class Items(

    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("name") var name: String? = null

)