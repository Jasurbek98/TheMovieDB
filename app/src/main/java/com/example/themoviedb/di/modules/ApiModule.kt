package com.example.themoviedb.di.modules

import com.example.themoviedb.data.remote.apis.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

}