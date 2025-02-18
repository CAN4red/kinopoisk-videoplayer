package com.example.videoplayerassignment.data.remote.dto

import kotlinx.serialization.SerialName

data class FilmsListInfo(
    @SerialName("total")
    val size: Int,
    @SerialName("totalPages")
    val chunksNumber: Int,
)
