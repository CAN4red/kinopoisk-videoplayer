package com.example.videoplayerassignment.domain.use_case

import com.example.videoplayerassignment.common.Resource
import kotlinx.coroutines.flow.Flow

object Utils {

    suspend fun <T> Flow<Resource<T>>.processFlow(): Resource<T> {
        var result: Resource<T> = Resource.Loading()
        this.collect { resource ->
            result = when (resource) {
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(resource.message)
                is Resource.Success -> Resource.Success(resource.data)
            }
        }
        return result
    }
}