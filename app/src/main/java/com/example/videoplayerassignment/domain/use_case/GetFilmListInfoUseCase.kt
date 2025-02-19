package com.example.videoplayerassignment.domain.use_case

import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmListInfo
import com.example.videoplayerassignment.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmListInfoUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(): Flow<Resource<FilmListInfo>> {
        return repository.getFilmsListInfo()
    }
}