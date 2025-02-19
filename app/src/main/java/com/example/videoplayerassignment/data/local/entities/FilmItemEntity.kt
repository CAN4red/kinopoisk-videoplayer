package com.example.videoplayerassignment.data.local.entities

import androidx.room.Entity

@Entity(tableName = "films")
data class FilmItemEntity(
    val id: Int,
    val name: String,
    val year: String,
    val posterUrlPreview: String,
)
