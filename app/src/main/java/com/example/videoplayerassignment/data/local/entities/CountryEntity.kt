package com.example.videoplayerassignment.data.local.entities

import androidx.room.Entity

@Entity(tableName = "countries")
data class CountryEntity(
    val id: Int,
    val name: String
)
