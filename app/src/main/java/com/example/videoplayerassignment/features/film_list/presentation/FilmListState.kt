package com.example.videoplayerassignment.features.film_list.presentation

import com.example.videoplayerassignment.features.film_list.domain.model.FilmItem

data class FilmListState(
    val films: List<FilmItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)