package com.example.videoplayerassignment.domain.use_case

import com.example.videoplayerassignment.common.Constants
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.model.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import com.example.videoplayerassignment.domain.use_case.Utils.processFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewFilmsUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(): Flow<Resource<List<FilmItem>>> = flow {
        emit(Resource.Loading())

        val filmListInfo = repository.getFilmListInfo().processFlow().data ?: FilmListInfo()
        val cachedFilms = repository.getAllFilmsFromCache().processFlow().data ?: emptyList<FilmItem>()

        val currentPage = cachedFilms.size / Constants.PAGE_LENGTH
        val nextPage = currentPage + 1

        if (filmListInfo.totalPages in 1..<nextPage) {
            emit(Resource.Error("There is no more films in the list"))
        } else {
            val newFilmsResource = repository.getFilmListByPage(nextPage).processFlow()
            repository.saveFilms(newFilmsResource.data ?: emptyList<FilmItem>())
            emit(newFilmsResource)
        }
    }
}