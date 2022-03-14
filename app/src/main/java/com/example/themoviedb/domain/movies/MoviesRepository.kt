package com.example.themoviedb.domain.movies

import com.example.themoviedb.data.remote.response.ResponsePopular
import com.example.themoviedb.data.remote.response.ResponseUpcoming
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
interface MoviesRepository {

    fun getPopularMovies(apiKey: String): Flow<Result<ResponsePopular>>

    fun getTopRatedMovies(apiKey: String): Flow<Result<ResponsePopular>>

    fun getUpcomingMovies(apiKey: String): Flow<Result<ResponseUpcoming>>
}