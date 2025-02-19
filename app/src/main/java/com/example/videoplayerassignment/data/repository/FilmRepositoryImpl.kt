package com.example.videoplayerassignment.data.repository

import coil.network.HttpException
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.data.remote.api.FilmApi
import com.example.videoplayerassignment.data.remote.dto.toFilmItemList
import com.example.videoplayerassignment.data.remote.dto.toFilmListInfo
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: FilmApi
) : FilmRepository {

    override fun getFilmsListInfo(): Flow<Resource<com.example.videoplayerassignment.domain.model.FilmListInfo>> = flow {
        try {
            emit(Resource.Loading<com.example.videoplayerassignment.domain.model.FilmListInfo>())
            val filmListInfo = api.getFilmsListInfo().toFilmListInfo()
            emit(Resource.Success<com.example.videoplayerassignment.domain.model.FilmListInfo>(filmListInfo))
        } catch (e: HttpException) {
            emit(Resource.Error<com.example.videoplayerassignment.domain.model.FilmListInfo>(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<com.example.videoplayerassignment.domain.model.FilmListInfo>("Could not reach server. Check your Internet connection"))
        }
    }

    override fun getFilmListByPage(page: Int): Flow<Resource<List<FilmItem>>> = flow {
        try {
            emit(Resource.Loading<List<FilmItem>>())
            val films = api.getFilmListByPage(page).toFilmItemList()
            emit(Resource.Success<List<FilmItem>>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<List<FilmItem>>(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<FilmItem>>("Could not reach server. Check your Internet connection"))
        }
    }
}