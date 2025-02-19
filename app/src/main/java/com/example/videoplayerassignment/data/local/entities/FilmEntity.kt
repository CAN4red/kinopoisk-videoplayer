package com.example.videoplayerassignment.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val year: Int?,
    val posterUrlPreview: String,
    @ColumnInfo(name = "insertion_order")
    val insertionOrder: Long = System.currentTimeMillis()
)
