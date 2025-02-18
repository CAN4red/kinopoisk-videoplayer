package com.example.videoplayerassignment.data.remote.dto

data class FilmsResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Film>,
)
