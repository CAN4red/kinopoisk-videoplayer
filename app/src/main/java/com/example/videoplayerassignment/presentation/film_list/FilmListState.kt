package com.example.videoplayerassignment.presentation.film_list

import com.example.videoplayerassignment.domain.model.FilmItem

data class FilmListState(
    val films: List<FilmItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)