package com.example.composeexampleapp.model.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "superhero_database")
data class SuperHero(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("modified")
    var modified: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = Thumbnail(),
    @ColumnInfo("isLiked")
    var isLiked: Boolean? = false,
    @ColumnInfo("isDisLiked")
    var isDisLiked: Boolean? = false,
    @SerializedName("resourceURI")
    var resourceURI: String? = null,
    @SerializedName("comics")
    var comics: Comics? = Comics(),
    @SerializedName("series")
    var series: Series? = Series(),
    @SerializedName("stories")
    var stories: Stories? = Stories(),
    @SerializedName("events")
    var events: Events? = Events(),
    @SerializedName("urls")
    var urls: ArrayList<Urls> = arrayListOf()
)