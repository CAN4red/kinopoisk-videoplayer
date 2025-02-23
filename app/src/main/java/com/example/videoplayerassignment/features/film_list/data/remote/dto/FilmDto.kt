package com.example.videoplayerassignment.features.film_list.data.remote.dto

import com.example.videoplayerassignment.core.data.remote.dto.CountryDto
import com.example.videoplayerassignment.core.data.remote.dto.GenreDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    @SerialName("kinopoiskId")
    val id: Int,
    @SerialName("nameRu")
    val name: String,
    val year: Int?,
    val countries: List<CountryDto>,
    val genres: List<GenreDto>,
    val posterUrlPreview: String,
)
