package com.example.videoplayerassignment.features.film_details.data.remote.dto

import com.example.videoplayerassignment.core.data.remote.dto.CountryDto
import com.example.videoplayerassignment.core.data.remote.dto.GenreDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailsDto(
    @SerialName("kinopoiskId")
    val id: Int,
    @SerialName("nameRu")
    val name: String,
    val year: Int?,
    val description: String,
    val countries: List<CountryDto>,
    val genres: List<GenreDto>,
)
