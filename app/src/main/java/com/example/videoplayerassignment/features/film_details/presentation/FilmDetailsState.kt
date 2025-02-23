package com.example.videoplayerassignment.features.film_details.presentation

import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails

data class FilmDetailsState(
    val filmDetails: FilmDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isVideoStarted: Boolean = false
) {
    val hasVideo: Boolean
        get() = filmDetails?.videos?.isNotEmpty() ?: false
}
