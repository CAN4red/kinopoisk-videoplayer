package com.example.videoplayerassignment.domain.use_case

import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmDetails
import com.example.videoplayerassignment.domain.repository.FilmDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmDetailsUseCase @Inject constructor(
    private val repository: FilmDetailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<FilmDetails>> {
        return repository.getFilmDetails(id)
    }
}