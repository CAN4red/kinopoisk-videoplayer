package com.example.videoplayerassignment.features.film_list.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.videoplayerassignment.features.film_list.data.local.dao.FilmDao
import com.example.videoplayerassignment.features.film_list.data.local.entities.CountryEntity
import com.example.videoplayerassignment.features.film_list.data.local.entities.FilmEntity
import com.example.videoplayerassignment.features.film_list.data.local.entities.GenreEntity

@Database(
    entities = [FilmEntity::class, CountryEntity::class, GenreEntity::class],
    version = 2,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {

    abstract val filmDao: FilmDao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = FilmDatabase::class.java,
                    name = "film_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}