package com.example.themoviedb.presentation.viewmodels.top_rated_fragment_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.domain.movies.MoviesRepository
import com.example.themoviedb.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */
@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var page = 1


    private val _topRatedMovie = MutableLiveData<List<MovieItem>>()
    val topRatedMovie: LiveData<List<MovieItem>> get() = _topRatedMovie

    private val _progressLiveData = MutableLiveData(false)
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData


    init {
        _topRatedMovie.postValue(emptyList())
    }


    fun initPopularMovie() {
        if (_progressLiveData.value == false) {
            _progressLiveData.value = true
            viewModelScope.launch(Dispatchers.IO) {
                moviesRepository.getTopRatedMovies(page).collect {
                    _progressLiveData.postValue(false)
                    it.onSuccess { response ->
                        page++
                        val tempList = _topRatedMovie.value
                        val generalList = ArrayList<MovieItem>(tempList)

//                        Log.d("TTT", "onSuccess ${response.results}")
                        generalList.addAll(response.results!!)
                        _topRatedMovie.postValue(generalList as List<MovieItem>)
                        _progressLiveData.postValue(false)
                    }

                    it.onFailure { throwable ->
                        _progressLiveData.postValue(false)
                        _errorLiveData.postValue(throwable)
                        Log.d("TTT", "$throwable")

                    }
                }
            }
        }
    }

}