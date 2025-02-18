package com.example.videoplayerassignment.domain.repository

import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.data.remote.dto.FilmsListInfo

interface FilmRepository {

    suspend fun getFilmsListInfo(): FilmsListInfo

    suspend fun getFilms(page: Int): List<Film>
}