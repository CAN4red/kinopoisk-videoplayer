package com.example.videoplayerassignment.data.remote.dto

import kotlinx.serialization.SerialName

data class Film(
    @SerialName("filmId")
    val id: Int,
    val nameRu: String,
    val nameEn: String?,
    val nameOriginal: String?,
    val ratingKinopoisk: Double?,
    @SerialName("ratingImbd")
    val ratingImdb: Double?,
    val year: String,
    val posterUrl: String,
    val posterUrlPreview: String,
)
