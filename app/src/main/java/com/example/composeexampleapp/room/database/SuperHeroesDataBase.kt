package com.example.composeexampleapp.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composeexampleapp.helpers.Converters
import com.example.composeexampleapp.reposatory.dataclasses.SuperHero
import com.example.composeexampleapp.room.dao.SuperHeroDao

@Database(entities = [SuperHero::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SuperheroDatabase : RoomDatabase() {

    abstract val superheroDao: SuperHeroDao

    companion object {
        @Volatile
        private var daoInstance: SuperHeroDao? = null

        private fun buildDataBase(context: Context): SuperheroDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                SuperheroDatabase::class.java,
                "superhero_database"
            ).fallbackToDestructiveMigration()
                .build()

        fun getDBInstance(context: Context): SuperHeroDao {
            synchronized(this) {
                if (daoInstance == null) {
                    daoInstance = buildDataBase(context).superheroDao
                }
                return daoInstance as SuperHeroDao

            }
        }
    }
}
