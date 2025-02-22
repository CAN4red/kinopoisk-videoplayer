package com.example.videoplayerassignment.presentation.film_list.components

object FilmListUtils {

    fun composeSubtitles(genres: List<String>, year: String, countries: List<String>): String {
        if (genres.isEmpty()) {
            return composeYearAndCountry(year, countries)
        }
        return genres.first().capitalizeFirstLetter() + composeYearAndCountry(year, countries)
    }

    private fun composeYearAndCountry(year: String, countries: List<String>): String {
        if (year.isBlank()) {
            return composeCountry(countries)
        }
        return " • $year" + composeCountry(countries)
    }

    private fun composeCountry(countries: List<String>): String {
        if (countries.isEmpty()) {
            return ""
        }
        return " • ${countries.first()}"
    }

    private fun String.capitalizeFirstLetter(): String {
        if (this.isNotEmpty()) {
            return this[0].uppercase() + this.substring(1).lowercase()
        }
        return this
    }
}