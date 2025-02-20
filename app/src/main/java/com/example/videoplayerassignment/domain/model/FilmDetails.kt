package com.example.videoplayerassignment.domain.model

data class FilmDetails(
    val id: Int,
    val name: String,
    val year: String,
    val description: String,
    val countries: List<String>,
    val genres: List<String>,
    val videoUrl: String,
)
