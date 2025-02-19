package com.example.videoplayerassignment.domain.repository

import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.model.FilmListInfo
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getFilmListInfo(): Flow<Resource<FilmListInfo>>

    fun getFilmListByPage(page: Int): Flow<Resource<List<FilmItem>>>

    fun getAllFilmsFromCache(): Flow<Resource<List<FilmItem>>>

    suspend fun saveFilms(films: List<FilmItem>)

    suspend fun clearCache()
}