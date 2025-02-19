package com.example.videoplayerassignment.data.remote.dto

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
