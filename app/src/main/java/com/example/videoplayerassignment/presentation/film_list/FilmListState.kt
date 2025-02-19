package com.example.videoplayerassignment.presentation.film_list


import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.model.FilmListInfo

data class FilmListState(
    val films: List<FilmItem> = emptyList(),
    val filmListInfo: FilmListInfo = FilmListInfo(),
    val isLoading: Boolean = false,
    val error: String? = null
)