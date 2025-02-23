package com.example.videoplayerassignment.features.film_details.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FilmVideoDto(
    val url: String,
    val name: String,
    val site: String,
)
