package com.example.themoviedb.data.sources.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themoviedb.R
import com.example.themoviedb.app.App
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.apis.MoviesApi
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.utils.API_KEY
import com.example.themoviedb.utils.timberLog

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */
/*
class MovieDataSource(
    private val moviesApi: MoviesApi,
    private val storage: LocalStorage
) : PagingSource<Int, MovieItem>() {

    private var emptyListener: ((Boolean) -> Unit)? = null


    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        timberLog("getRefreshKey = ${state.anchorPosition}")
        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = moviesApi.getPopularMovies(
                api_key = API_KEY,
                page = nextPageNumber,
            )
            emptyListener?.invoke(response.body()?.results!!.isEmpty())
            LoadResult.Page(
                data = response.body()?.results,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.body()?.data?.allCommentsCount!!)
                    nextPageNumber + 1 else null

            )
        } catch (e: Exception) {
            LoadResult.Error(Exception(App.instance.resources.getString(R.string.connection_retry)))
        }
    }

    fun emptyFun(f: (Boolean) -> Unit) {
        emptyListener = f
    }
}*/
