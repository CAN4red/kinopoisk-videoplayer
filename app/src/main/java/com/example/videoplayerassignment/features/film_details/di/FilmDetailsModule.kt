package com.example.videoplayerassignment.features.film_details.di

import com.example.videoplayerassignment.features.film_details.data.remote.api.FilmDetailsApi
import com.example.videoplayerassignment.features.film_details.data.repository.FilmDetailsRepositoryImpl
import com.example.videoplayerassignment.features.film_details.domain.repository.FilmDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmDetailsModule {

    @Provides
    @Singleton
    fun provideFilmDetailsApi(retrofit: Retrofit): FilmDetailsApi {
        return retrofit.create(FilmDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmDetailsRepository(api: FilmDetailsApi): FilmDetailsRepository {
        return FilmDetailsRepositoryImpl(api)
    }
}