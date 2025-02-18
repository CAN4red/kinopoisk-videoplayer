package com.example.videoplayerassignment.data.remote.dto

import kotlinx.serialization.SerialName

data class FilmsList(
    @SerialName("items")
    val films: List<Film>,
)
