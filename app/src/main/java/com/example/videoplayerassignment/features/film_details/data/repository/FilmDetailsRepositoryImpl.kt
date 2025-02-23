package com.example.videoplayerassignment.features.film_details.data.repository

import coil.network.HttpException
import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_details.data.mappers.FilmDetailsMapper
import com.example.videoplayerassignment.features.film_details.data.remote.api.FilmDetailsApi
import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails
import com.example.videoplayerassignment.features.film_details.domain.repository.FilmDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FilmDetailsRepositoryImpl @Inject constructor(
    private val api: FilmDetailsApi
) : FilmDetailsRepository {

    override fun getFilmDetails(id: Int): Flow<Resource<FilmDetails>> = flow {
        try {
            emit(Resource.Loading())
            val filmDetailsDto = api.getFilmDetails(id)
            val filmVideosDto = api.getFilmVideos(id)
            val filmDetails = FilmDetailsMapper.dtoToDomain(filmDetailsDto, filmVideosDto)
            emit(Resource.Success(filmDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}