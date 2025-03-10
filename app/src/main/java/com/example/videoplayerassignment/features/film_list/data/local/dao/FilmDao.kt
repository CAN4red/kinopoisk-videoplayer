package com.example.videoplayerassignment.features.film_list.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.videoplayerassignment.features.film_list.data.local.entities.CountryEntity
import com.example.videoplayerassignment.features.film_list.data.local.entities.FilmEntity
import com.example.videoplayerassignment.features.film_list.data.local.entities.GenreEntity
import com.example.videoplayerassignment.features.film_list.data.local.relations.FilmWithCountriesAndGenresEntity

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Transaction
    @Query("DELETE FROM films")
    suspend fun deleteAllFilms()

    @Transaction
    @Query("SELECT * FROM films ORDER BY insertion_order ASC")
    suspend fun getAllFilms(): List<FilmWithCountriesAndGenresEntity>
}