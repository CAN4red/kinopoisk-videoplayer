package com.example.videoplayerassignment.features.film_details.domain.model

data class FilmDetails(
    val id: Int,
    val name: String,
    val year: String,
    val description: String,
    val countries: List<String>,
    val genres: List<String>,
    val videos: List<FilmVideo>,
)
