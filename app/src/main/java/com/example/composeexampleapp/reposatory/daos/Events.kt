package com.example.composeexampleapp.reposatory.daos

import com.google.gson.annotations.SerializedName


data class Events(

    @SerializedName("available") var available: Int? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf(),
    @SerializedName("returned") var returned: Int? = null

)