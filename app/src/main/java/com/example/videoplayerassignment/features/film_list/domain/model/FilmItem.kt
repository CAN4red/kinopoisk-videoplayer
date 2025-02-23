package com.example.videoplayerassignment.features.film_list.domain.model

data class FilmItem(
    val id: Int,
    val name: String,
    val year: String,
    val countries: List<String>,
    val genres: List<String>,
    val posterUrlPreview: String,
)
