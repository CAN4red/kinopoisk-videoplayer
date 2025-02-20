package com.example.videoplayerassignment.presentation.film_details

import com.example.videoplayerassignment.domain.model.FilmDetails

data class FilmDetailsState(
    val filmDetails: FilmDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
