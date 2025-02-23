package com.example.videoplayerassignment.features.film_list.domain.repository

import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_list.domain.model.FilmItem
import com.example.videoplayerassignment.features.film_list.domain.model.FilmListInfo
import kotlinx.coroutines.flow.Flow

interface FilmListRepository {

    fun getFilmListInfo(): Flow<Resource<FilmListInfo>>

    fun getFilmListByPage(page: Int): Flow<Resource<List<FilmItem>>>

    fun getAllFilmsFromCache(): Flow<Resource<List<FilmItem>>>

    suspend fun saveFilms(films: List<FilmItem>)

    suspend fun clearCache()
}