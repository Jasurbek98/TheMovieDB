package com.example.themoviedb.presentation.viewmodels.popular_fragment_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.local_models.dashboard.DataWrapper
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.domain.movies.MoviesRepository
import com.example.themoviedb.utils.API_KEY
import com.example.themoviedb.utils.`typealias`.MutableStateFlowWrapper
import com.example.themoviedb.utils.`typealias`.StateFlowWrapper
import com.example.themoviedb.utils.coroutinescope.launchCoroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */
@HiltViewModel
class PopularViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {


    private val _popularMovie = MutableLiveData<List<MovieItem>>()
    val popularMovie: LiveData<List<MovieItem>> get() = _popularMovie

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData


/*    init {
        initPopularMovie()
    }*/


    fun initPopularMovie() {
        _progressLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getPopularMovies().collect {
                _progressLiveData.postValue(false)
                it.onSuccess { response ->
                    Log.d("TTT", "onSuccess")
                    _popularMovie.postValue(response.results!!)
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