package com.example.themoviedb.data.remote.apis

import com.example.themoviedb.data.remote.response.*
import com.example.themoviedb.utils.API_KEY
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */

interface MoviesApi {

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


    @GET("person/{person_id}")
    suspend fun getActor(
        @Path("person_id") personId: Int
    ): Response<ResponsePerson>


    @GET("person/{person_id}/movie_credits")
    suspend fun getActorCredits(
        @Path("person_id") personId: Int
    ): Response<ResponseActorCredits>


}