package com.example.videoplayerassignment.domain.use_case

import android.util.Log
import com.example.videoplayerassignment.common.Constants
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.data.remote.dto.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetNewFilmPagesUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(
        currentFilms: List<Film>,
        filmListInfo: FilmListInfo
    ): Flow<Resource<List<Film>>> {

        val currentPage = currentFilms.size / Constants.PAGE_LENGTH
        val nextPage = currentPage + 1

        if (nextPage > filmListInfo.totalPages) {
            return flow {
                emit(Resource.Success<List<Film>>(currentFilms))
            }
        }

        return repository.getFilmListByPage(nextPage).map { result ->
            when (result) {
                is Resource.Loading -> {
                    Resource.Loading<List<Film>>(data = currentFilms)
                }

                is Resource.Success -> {
                    val combinedList = currentFilms + result.data
                    Log.i("Combined List", combinedList.size.toString())
                    Resource.Success<List<Film>>(combinedList)
                }

                is Resource.Error -> {
                    Resource.Error<List<Film>>(
                        message = result.message,
                        data = currentFilms,
                    )
                }
            }
        }
    }
}