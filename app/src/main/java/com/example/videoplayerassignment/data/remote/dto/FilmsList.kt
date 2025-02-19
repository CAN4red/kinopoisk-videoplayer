package com.example.videoplayerassignment.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsList(
    @SerialName("items")
    val films: List<FilmDto>,
)

fun FilmsList.toFilmItemList() = films.map { it.toFilmItem() }
