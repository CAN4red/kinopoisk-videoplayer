package com.example.videoplayerassignment.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val year: String,
    val posterUrlPreview: String,
)
