package com.example.themoviedb.data.remote.apis

import com.example.themoviedb.data.remote.response.ResponsePopular
import com.example.themoviedb.data.remote.response.ResponseUpcoming
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
interface MoviesApi {

    //    @Headers("api-key: " + "PUT_YOUR_API_KEY")
    @GET("movie/popular?api_key={api_key}")
    fun getPopularMovies(
        @Header("api_key") api_key: String,
//        @Path("page") page: Int,
    ): Response<ResponsePopular>


    @GET("movie/popular?api_key={api_key}")
    fun getTopRatedMovies(@Header("api_key") api_key: String): Response<ResponsePopular>


    @GET("movie/popular?api_key={api_key}")
    fun getUpcomingMovies(@Header("api_key") api_key: String): Response<ResponseUpcoming>


}