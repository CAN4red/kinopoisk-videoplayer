package com.example.videoplayerassignment.data.mappers

import com.example.videoplayerassignment.data.local.entities.CountryEntity
import com.example.videoplayerassignment.data.local.entities.FilmEntity
import com.example.videoplayerassignment.data.local.entities.GenreEntity
import com.example.videoplayerassignment.data.local.relations.FilmWithCountriesAndGenresEntity
import com.example.videoplayerassignment.data.remote.dto.FilmDto
import com.example.videoplayerassignment.domain.model.FilmItem

object FilmMapper {
    fun dtoToFilmEntity(dto: FilmDto) = FilmEntity(
        id = dto.id,
        name = dto.name,
        year = dto.year,
        posterUrlPreview = dto.posterUrlPreview
    )

    fun dtoToCountryEntitiesList(dto: FilmDto): List<CountryEntity> {
        return dto.countries.map {
            CountryEntity(id = dto.id, name = it.name)
        }
    }

    fun dtoToGenresEntitiesList(dto: FilmDto): List<GenreEntity> {
        return dto.genres.map {
            GenreEntity(id = dto.id, name = it.name)
        }
    }

    fun entityToDomain(entity: FilmWithCountriesAndGenresEntity) = FilmItem(
        id = entity.filmEntity.id,
        name = entity.filmEntity.name,
        year = entity.filmEntity.year?.toString() ?: "",
        countries = entity.countryEntities.map { it.name },
        genres = entity.genreEntities.map { it.name },
        posterUrlPreview = entity.filmEntity.posterUrlPreview
    )

    fun dtoToDomain(dto: FilmDto) = FilmItem(
        id = dto.id,
        name = dto.name,
        year = dto.year?.toString() ?: "",
        countries = dto.countries.map { it.name },
        genres = dto.genres.map { it.name },
        posterUrlPreview = dto.posterUrlPreview
    )
}