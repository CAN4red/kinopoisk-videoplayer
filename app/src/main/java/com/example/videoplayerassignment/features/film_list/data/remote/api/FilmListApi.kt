package com.example.videoplayerassignment.features.film_list.data.remote.api

import com.example.videoplayerassignment.features.film_list.data.remote.dto.FilmListInfoDto
import com.example.videoplayerassignment.features.film_list.data.remote.dto.FilmsList
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmListApi {

    @GET("api/v2.2/films/collections?type=$COLLECTION_TYPE&page=1")
    suspend fun getFilmsListInfo(): FilmListInfoDto

    @GET("api/v2.2/films/collections")
    suspend fun getFilmListByPage(
        @Query("page") page: Int,
        @Query("type") collectionType: String = COLLECTION_TYPE
    ): FilmsList

    companion object {
        private const val COLLECTION_TYPE = "TOP_POPULAR_MOVIES"
    }
}