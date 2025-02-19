package com.example.videoplayerassignment.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "countries",
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)
