package com.example.videoplayerassignment.features.film_list.data.remote.dto

import com.example.videoplayerassignment.features.film_list.domain.model.FilmListInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmListInfoDto(
    @SerialName("total")
    val size: Int,
    val totalPages: Int,
)

fun FilmListInfoDto.toFilmListInfo() = FilmListInfo(
    size = size,
    totalPages = totalPages,
)
