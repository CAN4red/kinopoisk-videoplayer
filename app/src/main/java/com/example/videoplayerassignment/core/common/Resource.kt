package com.example.videoplayerassignment.core.common

sealed interface Resource<T> {
    val data: T?

    data class Success<T>(override val data: T) : Resource<T>
    data class Error<T>(val message: String, override val data: T? = null) : Resource<T>
    data class Loading<T>(override val data: T? = null) : Resource<T>
}