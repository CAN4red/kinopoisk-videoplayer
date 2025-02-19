package com.example.videoplayerassignment.data.repository

import coil.network.HttpException
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.data.local.dao.FilmDao
import com.example.videoplayerassignment.data.mappers.FilmMapper
import com.example.videoplayerassignment.data.remote.api.FilmApi
import com.example.videoplayerassignment.data.remote.dto.toFilmItemList
import com.example.videoplayerassignment.data.remote.dto.toFilmListInfo
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.model.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: FilmApi,
    private val dao: FilmDao,
) : FilmRepository {

    override fun getFilmListInfo(): Flow<Resource<FilmListInfo>> = flow {
        try {
            emit(Resource.Loading<FilmListInfo>())
            val filmListInfo = api.getFilmsListInfo().toFilmListInfo()
            emit(Resource.Success<FilmListInfo>(filmListInfo))
        } catch (e: HttpException) {
            emit(
                Resource.Error<FilmListInfo>(
                    e.localizedMessage ?: "An unexpected Http error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<FilmListInfo>("Could not reach server. Check your Internet connection"))
        }
    }

    override fun getFilmListByPage(page: Int): Flow<Resource<List<FilmItem>>> = flow {
        try {
            emit(Resource.Loading<List<FilmItem>>())
            val films = api.getFilmListByPage(page).toFilmItemList()
            emit(Resource.Success<List<FilmItem>>(films))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<FilmItem>>(
                    e.localizedMessage ?: "An unexpected Http error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<FilmItem>>("Could not reach server. Check your Internet connection"))
        }
    }

    override fun getAllFilmsFromCache(): Flow<Resource<List<FilmItem>>> = flow {
        try {
            emit(Resource.Loading<List<FilmItem>>())
            val filmsFromCache = dao.getAllFilms().map { film -> FilmMapper.entityToDomain(film) }
            emit(Resource.Success<List<FilmItem>>(filmsFromCache))
        } catch (e: Exception) {
            emit(Resource.Error<List<FilmItem>>(e.localizedMessage ?: "Unknown error occurred"))
        }
    }

    override suspend fun saveFilms(films: List<FilmItem>) {
        val filmEntities = films.map { film -> FilmMapper.domainToFilmEntity(film) }
        val countryEntities = films.flatMap { film -> FilmMapper.domainToCountryEntitiesList(film) }
        val genreEntities = films.flatMap { film -> FilmMapper.domainToGenresEntitiesList(film) }

        coroutineScope {
            filmEntities.forEach {
                filmEntity -> dao.insertFilm(filmEntity)
//                delay(1)
            }
        }
        countryEntities.forEach { countryEntity -> dao.insertCountry(countryEntity) }
        genreEntities.forEach { genreEntity -> dao.insertGenre(genreEntity) }
    }

    override suspend fun clearCache() {
        dao.deleteAllFilms()
    }
}