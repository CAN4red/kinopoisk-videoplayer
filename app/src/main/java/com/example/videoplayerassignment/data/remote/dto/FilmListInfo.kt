package com.example.videoplayerassignment.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmListInfo(
    @SerialName("total")
    val size: Int = 0,
    val totalPages: Int = 0,
)
