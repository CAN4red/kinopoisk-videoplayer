package com.example.videoplayerassignment.domain.repository

import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmVideoRepository {

    fun getFilmDetails(id: Int): Flow<Resource<FilmDetails>>
}