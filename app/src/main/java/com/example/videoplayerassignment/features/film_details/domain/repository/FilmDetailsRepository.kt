package com.example.videoplayerassignment.features.film_details.domain.repository

import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmDetailsRepository {

    fun getFilmDetails(id: Int): Flow<Resource<FilmDetails>>
}