package com.example.videoplayerassignment.features.film_list.domain.use_case

import com.example.videoplayerassignment.core.common.Resource
import kotlinx.coroutines.flow.Flow

object Utils {

    suspend fun <T> Flow<Resource<T>>.processFlow(): Resource<T> {
        var result: Resource<T> = Resource.Loading()
        this.collect { resource ->
            result = when (resource) {
                is Resource.Loading -> Resource.Loading(resource.data)
                is Resource.Error -> Resource.Error(resource.message, resource.data)
                is Resource.Success -> Resource.Success(resource.data)
            }
        }
        return result
    }
}