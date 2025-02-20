package com.example.videoplayerassignment.di

import android.content.Context
import com.example.videoplayerassignment.common.Constants
import com.example.videoplayerassignment.data.local.FilmDatabase
import com.example.videoplayerassignment.data.local.dao.FilmDao
import com.example.videoplayerassignment.data.remote.api.FilmApi
import com.example.videoplayerassignment.data.repository.FilmDetailsRepositoryImpl
import com.example.videoplayerassignment.data.repository.FilmRepositoryImpl
import com.example.videoplayerassignment.di.ApiAccess.API_KEY
import com.example.videoplayerassignment.domain.repository.FilmDetailsRepository
import com.example.videoplayerassignment.domain.repository.FilmRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFilmsApi(okHttpClient: OkHttpClient): FilmApi {
        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(FilmApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-API-KEY", API_KEY)
                    .addHeader("accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideFilmRepository(api: FilmApi, dao: FilmDao): FilmRepository {
        return FilmRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideFilmDetailsRepository(api: FilmApi): FilmDetailsRepository {
        return FilmDetailsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFilmDatabase(@ApplicationContext context: Context): FilmDatabase {
        return FilmDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFilmDao(db: FilmDatabase): FilmDao {
        return db.filmDao
    }
}