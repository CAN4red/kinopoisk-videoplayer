package com.example.videoplayerassignment.data.remote.dto

import com.example.videoplayerassignment.domain.model.FilmItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    @SerialName("kinopoiskId")
    val id: Int,
    @SerialName("nameRu")
    val name: String,
    val year: Int?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val posterUrlPreview: String,
)

fun FilmDto.toFilmItem() = FilmItem(
    id = id,
    name = name,
    year = year?.toString() ?: "",
    countries = countries.map { it.name },
    genres = genres.map { it.name },
    posterUrlPreview = posterUrlPreview
)
