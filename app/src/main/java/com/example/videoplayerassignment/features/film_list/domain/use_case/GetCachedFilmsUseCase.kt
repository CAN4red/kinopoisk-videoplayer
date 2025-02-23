package com.example.videoplayerassignment.features.film_list.domain.use_case

import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_list.domain.model.FilmItem
import com.example.videoplayerassignment.features.film_list.domain.repository.FilmListRepository
import com.example.videoplayerassignment.features.film_list.domain.use_case.Utils.processFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCachedFilmsUseCase @Inject constructor(
    private val repository: FilmListRepository
) {
    operator fun invoke(): Flow<Resource<List<FilmItem>>> = flow {
        emit(Resource.Loading())

        val cachedFilmsResource = repository.getAllFilmsFromCache().processFlow()

        emit(cachedFilmsResource)
    }
}