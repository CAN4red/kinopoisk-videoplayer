package com.example.videoplayerassignment.features.film_list.data.repository

import coil.network.HttpException
import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_list.data.local.dao.FilmDao
import com.example.videoplayerassignment.features.film_list.data.mappers.FilmMapper
import com.example.videoplayerassignment.features.film_list.data.remote.api.FilmListApi
import com.example.videoplayerassignment.features.film_list.data.remote.dto.toFilmItemList
import com.example.videoplayerassignment.features.film_list.data.remote.dto.toFilmListInfo
import com.example.videoplayerassignment.features.film_list.domain.model.FilmItem
import com.example.videoplayerassignment.features.film_list.domain.model.FilmListInfo
import com.example.videoplayerassignment.features.film_list.domain.repository.FilmListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FilmListListRepositoryImpl @Inject constructor(
    private val api: FilmListApi,
    private val dao: FilmDao,
) : FilmListRepository {

    override fun getFilmListInfo(): Flow<Resource<FilmListInfo>> = flow {
        try {
            emit(Resource.Loading())
            val filmListInfo = api.getFilmsListInfo().toFilmListInfo()
            emit(Resource.Success(filmListInfo))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }

    override fun getFilmListByPage(page: Int): Flow<Resource<List<FilmItem>>> = flow {
        try {
            emit(Resource.Loading())
            val films = api.getFilmListByPage(page).toFilmItemList()
            emit(Resource.Success(films))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }

    override fun getAllFilmsFromCache(): Flow<Resource<List<FilmItem>>> = flow {
        try {
            emit(Resource.Loading())
            val filmsFromCache = dao.getAllFilms().map { film -> FilmMapper.entityToDomain(film) }
            emit(Resource.Success(filmsFromCache))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
    }

    override suspend fun saveFilms(films: List<FilmItem>) {
        val filmEntities = films.map { film -> FilmMapper.domainToFilmEntity(film) }
        val countryEntities = films.flatMap { film -> FilmMapper.domainToCountryEntitiesList(film) }
        val genreEntities = films.flatMap { film -> FilmMapper.domainToGenresEntitiesList(film) }

        filmEntities.forEach { filmEntity -> dao.insertFilm(filmEntity) }
        countryEntities.forEach { countryEntity -> dao.insertCountry(countryEntity) }
        genreEntities.forEach { genreEntity -> dao.insertGenre(genreEntity) }
    }

    override suspend fun clearCache() {
        dao.deleteAllFilms()
    }
}