package com.example.videoplayerassignment.features.film_details.domain.use_case

import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails
import com.example.videoplayerassignment.features.film_details.domain.repository.FilmDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmDetailsUseCase @Inject constructor(
    private val repository: FilmDetailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<FilmDetails>> {
        return repository.getFilmDetails(id)
    }
}