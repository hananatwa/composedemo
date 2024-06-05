package com.example.composeexampleapp.model.dataclasses

import com.google.gson.annotations.SerializedName


data class MarvelData(
    @SerializedName("data") var data: Data? = Data()
)