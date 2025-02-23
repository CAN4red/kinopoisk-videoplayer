package com.example.videoplayerassignment.features.film_details.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmVideosDto(
    val total: Int,
    @SerialName("items")
    val videos: List<FilmVideoDto>
)
