package com.example.videoplayerassignment.domain.use_case

import android.util.Log
import com.example.videoplayerassignment.common.Constants
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.model.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNewFilmPagesUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(
        currentFilms: List<FilmItem>,
        filmListInfo: FilmListInfo
    ): Flow<Resource<List<FilmItem>>> {

        val currentPage = currentFilms.size / Constants.PAGE_LENGTH
        val nextPage = currentPage + 1

        if (nextPage > filmListInfo.totalPages) {
            return flow {
                emit(Resource.Success<List<FilmItem>>(currentFilms))
            }
        }

        return repository.getFilmListByPage(nextPage).map { result ->
            when (result) {
                is Resource.Loading -> {
                    Resource.Loading<List<FilmItem>>(data = currentFilms)
                }

                is Resource.Success -> {
                    val combinedList = currentFilms + result.data
                    Log.i("Combined List", combinedList.size.toString())
                    Resource.Success<List<FilmItem>>(combinedList)
                }

                is Resource.Error -> {
                    Resource.Error<List<FilmItem>>(
                        message = result.message,
                        data = currentFilms,
                    )
                }
            }
        }
    }
}