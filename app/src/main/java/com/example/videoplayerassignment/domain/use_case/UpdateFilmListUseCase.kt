package com.example.videoplayerassignment.domain.use_case

import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.repository.FilmRepository
import com.example.videoplayerassignment.domain.use_case.Utils.processFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateFilmListUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(): Flow<Resource<List<FilmItem>>> = flow {
        emit(Resource.Loading())

        val updatedFilmsResource = repository.getFilmListByPage(1).processFlow()
        emit(updatedFilmsResource)

        if (updatedFilmsResource is Resource.Success) {
            repository.clearCache()
            repository.saveFilms(updatedFilmsResource.data)
        }
    }
}