package com.example.videoplayerassignment.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    @SerialName("kinopoiskId")
    val id: Int,
    val nameRu: String,
    val nameEn: String?,
    val year: Int?,
    val posterUrl: String,
    val posterUrlPreview: String,
)
