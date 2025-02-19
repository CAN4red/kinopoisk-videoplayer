package com.example.videoplayerassignment.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.videoplayerassignment.data.local.entities.CountryEntity
import com.example.videoplayerassignment.data.local.entities.FilmItemEntity
import com.example.videoplayerassignment.data.local.entities.GenreEntity

data class FilmItemWithCountriesAndGenresEntity(
    @Embedded val filmItemEntity: FilmItemEntity,
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
