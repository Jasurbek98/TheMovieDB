package com.example.themoviedb.data.remote.apis

import com.example.themoviedb.data.remote.response.ResponseActors
import com.example.themoviedb.data.remote.response.ResponseMovieDetails
import com.example.themoviedb.data.remote.response.ResponsePopular
import com.example.themoviedb.data.remote.response.ResponseUpcoming
import com.example.themoviedb.utils.API_KEY
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */

interface MoviesApi {

    //    @Headers(API_KEY)
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<ResponsePopular>


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<ResponsePopular>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<ResponseUpcoming>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") movieId: Int
    ): Response<ResponseMovieDetails>


    @GET("movie/{id}/credits")
    suspend fun getMovieActors(
        @Path("id") id: Int
    ): Response<ResponseActors>


}