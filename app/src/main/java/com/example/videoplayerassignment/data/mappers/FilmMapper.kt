package com.example.videoplayerassignment.data.mappers

import com.example.videoplayerassignment.data.local.entities.CountryEntity
import com.example.videoplayerassignment.data.local.entities.FilmEntity
import com.example.videoplayerassignment.data.local.entities.GenreEntity
import com.example.videoplayerassignment.data.local.relations.FilmWithCountriesAndGenresEntity
import com.example.videoplayerassignment.data.remote.dto.FilmDto
import com.example.videoplayerassignment.domain.model.FilmItem

object FilmMapper {
    fun domainToFilmEntity(filmItem: FilmItem) = FilmEntity(
        id = filmItem.id,
        name = filmItem.name,
        year = filmItem.year.toIntYear(),
        posterUrlPreview = filmItem.posterUrlPreview
    )

    fun domainToCountryEntitiesList(filmItem: FilmItem): List<CountryEntity> {
        return filmItem.countries.map { countryString ->
            CountryEntity(id = filmItem.id, name = countryString)
        }
    }

    fun domainToGenresEntitiesList(filmItem: FilmItem): List<GenreEntity> {
        return filmItem.genres.map { genreString ->
            GenreEntity(id = filmItem.id, name = genreString)
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

    private fun String.toIntYear(): Int? {
        return try {
            this.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}