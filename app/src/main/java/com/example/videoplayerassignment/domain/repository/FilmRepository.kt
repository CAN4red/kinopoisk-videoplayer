package com.example.videoplayerassignment.domain.repository

import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.data.remote.dto.FilmListInfo
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getFilmsListInfo(): Flow<Resource<FilmListInfo>>

    fun getFilmListByPage(page: Int): Flow<Resource<List<Film>>>
}