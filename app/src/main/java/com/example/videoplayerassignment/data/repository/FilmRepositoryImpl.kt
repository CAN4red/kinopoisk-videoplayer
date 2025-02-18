package com.example.videoplayerassignment.data.repository

import com.example.videoplayerassignment.data.remote.api.FilmApi
import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.data.remote.dto.FilmsListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: FilmApi
) : FilmRepository {

    override suspend fun getFilmsListInfo(): FilmsListInfo {
        return api.getFilmsListInfo()
    }

    override suspend fun getFilms(page: Int): List<Film> {
        return api.getFilmsList(page).films
    }
}