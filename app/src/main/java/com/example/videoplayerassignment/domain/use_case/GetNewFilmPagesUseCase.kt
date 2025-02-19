package com.example.videoplayerassignment.domain.use_case

import com.example.videoplayerassignment.common.Constants
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.model.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewFilmPagesUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(): Flow<Resource<List<FilmItem>>> = flow {
        emit(Resource.Loading())

        val filmsFromCache = repository.getAllFilmsFromCache()

        val currentFilms = filmsFromCache.processFilmsFromCache().data ?: emptyList()
        val filmListInfo = repository.getFilmListInfo()

        emit(filmListInfo.processFilmListInfo(currentFilms))
    }

    private suspend fun Flow<Resource<List<FilmItem>>>.processFilmsFromCache(): Resource<List<FilmItem>> {
        var result: Resource<List<FilmItem>> = Resource.Loading()
        this.collect { resource ->
            result = when (resource) {
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(resource.message)
                is Resource.Success -> Resource.Loading(resource.data)
            }
        }
        return result
    }

    private suspend fun Flow<Resource<FilmListInfo>>.processFilmListInfo(data: List<FilmItem>): Resource<List<FilmItem>> {
        var result: Resource<List<FilmItem>> = Resource.Loading(data)
        this.collect { resource ->
            result = when (resource) {
                is Resource.Loading -> Resource.Loading(data)
                is Resource.Error -> Resource.Error(message = resource.message, data = data)
                is Resource.Success -> processNewPages(data = data, filmListInfo = resource.data)
            }
        }
        return result
    }

    private suspend fun processNewPages(
        data: List<FilmItem>,
        filmListInfo: FilmListInfo
    ): Resource<List<FilmItem>> {
        val currentPage = data.size / Constants.PAGE_LENGTH
        val nextPage = currentPage + 1

        if (nextPage > filmListInfo.totalPages) {
            return Resource.Success(data)
        }

        var result: Resource<List<FilmItem>> = Resource.Loading(data)
        repository.getFilmListByPage(nextPage).collect { resource ->
            result = when (resource) {
                is Resource.Loading -> Resource.Loading(data)
                is Resource.Error -> Resource.Error(message = resource.message, data = data)
                is Resource.Success -> processGettingNewFilms(
                    cachedFilms = data,
                    newFilms = resource.data
                )
            }
        }
        return result
    }

    private suspend fun processGettingNewFilms(
        cachedFilms: List<FilmItem>,
        newFilms: List<FilmItem>
    ): Resource<List<FilmItem>> {
        repository.saveFilms(newFilms)
        var result: Resource<List<FilmItem>> = Resource.Loading(cachedFilms)

        repository.getAllFilmsFromCache().collect { resource ->
            result = when (resource) {
                is Resource.Loading -> Resource.Loading(cachedFilms)
                is Resource.Error -> Resource.Error(message = resource.message, data = cachedFilms)
                is Resource.Success -> Resource.Success(resource.data)
            }
        }
        return result
    }
}


