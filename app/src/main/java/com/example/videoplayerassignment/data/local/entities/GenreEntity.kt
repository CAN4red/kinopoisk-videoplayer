package com.example.videoplayerassignment.data.local.entities

import androidx.room.Entity

@Entity(tableName = "genres")
data class GenreEntity(
    val id: Int,
    val name: String,
)
