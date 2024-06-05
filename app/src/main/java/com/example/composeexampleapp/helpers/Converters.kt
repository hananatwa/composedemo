package com.example.composeexampleapp.helpers


import androidx.room.TypeConverter
import com.example.composeexampleapp.reposatory.dataclasses.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail?): String? {
        return Gson().toJson(thumbnail)
    }

    @TypeConverter
    fun toThumbnail(thumbnailString: String?): Thumbnail? {
        val type = object : TypeToken<Thumbnail>() {}.type
        return Gson().fromJson(thumbnailString, type)
    }

    @TypeConverter
    fun fromComics(comics: Comics?): String? {
        return Gson().toJson(comics)
    }

    @TypeConverter
    fun toComics(comicsString: String?): Comics? {
        val type = object : TypeToken<Comics>() {}.type
        return Gson().fromJson(comicsString, type)
    }

    @TypeConverter
    fun fromSeries(series: Series?): String? {
        return Gson().toJson(series)
    }

    @TypeConverter
    fun toSeries(seriesString: String?): Series? {
        val type = object : TypeToken<Series>() {}.type
        return Gson().fromJson(seriesString, type)
    }

    @TypeConverter
    fun fromStories(stories: Stories?): String? {
        return Gson().toJson(stories)
    }

    @TypeConverter
    fun toStories(storiesString: String?): Stories? {
        val type = object : TypeToken<Stories>() {}.type
        return Gson().fromJson(storiesString, type)
    }

    @TypeConverter
    fun fromEvents(events: Events?): String? {
        return Gson().toJson(events)
    }

    @TypeConverter
    fun toEvents(eventsString: String?): Events? {
        val type = object : TypeToken<Events>() {}.type
        return Gson().fromJson(eventsString, type)
    }

    @TypeConverter
    fun fromUrls(urls: ArrayList<Urls>?): String? {
        return Gson().toJson(urls)
    }

    @TypeConverter
    fun toUrls(urlsString: String?): ArrayList<Urls>? {
        val type = object : TypeToken<ArrayList<Urls>>() {}.type
        return Gson().fromJson(urlsString, type)
    }
}
