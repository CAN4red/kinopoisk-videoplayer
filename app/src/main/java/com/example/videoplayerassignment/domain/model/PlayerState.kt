package com.example.videoplayerassignment.domain.model

data class PlayerState(
    val isPlaying: Boolean = false,
    val duration: Long = 0L,
    val currentPosition: Long = 0L,
)
