package com.example.videoplayerassignment.presentation.film_list

import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.data.remote.dto.FilmListInfo

data class FilmListState(
    val films: List<Film> = emptyList(),
    val filmListInfo: FilmListInfo = FilmListInfo(),
    val isLoading: Boolean = false,
    val error: String? = null
)