package com.example.videoplayerassignment.data.repository

import coil.network.HttpException
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.data.remote.api.FilmApi
import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.data.remote.dto.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: FilmApi
) : FilmRepository {

    override fun getFilmsListInfo(): Flow<Resource<FilmListInfo>> = flow {
        try {
            emit(Resource.Loading<FilmListInfo>())
            val filmListInfo = api.getFilmsListInfo()
            emit(Resource.Success<FilmListInfo>(filmListInfo))
        } catch (e: HttpException) {
            emit(Resource.Error<FilmListInfo>(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<FilmListInfo>("Could not reach server. Check your Internet connection"))
        }
    }

    override fun getFilmListByPage(page: Int): Flow<Resource<List<Film>>> = flow {
        try {
            emit(Resource.Loading<List<Film>>())
            val films = api.getFilmListByPage(page).films
            emit(Resource.Success<List<Film>>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Film>>(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Film>>("Could not reach server. Check your Internet connection"))
        }
    }
}