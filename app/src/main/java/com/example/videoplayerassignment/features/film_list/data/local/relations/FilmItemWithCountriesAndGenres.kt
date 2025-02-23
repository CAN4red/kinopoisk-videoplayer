package com.example.videoplayerassignment.features.film_list.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.videoplayerassignment.features.film_list.data.local.entities.CountryEntity
import com.example.videoplayerassignment.features.film_list.data.local.entities.FilmEntity
import com.example.videoplayerassignment.features.film_list.data.local.entities.GenreEntity

data class FilmWithCountriesAndGenresEntity(
    @Embedded val filmEntity: FilmEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val countryEntities: List<CountryEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val genreEntities: List<GenreEntity>
)
