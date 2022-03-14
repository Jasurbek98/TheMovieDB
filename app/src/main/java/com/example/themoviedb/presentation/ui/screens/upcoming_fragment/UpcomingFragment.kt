package com.example.themoviedb.presentation.ui.screens.upcoming_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.databinding.FragmentUpcomingBinding
import com.example.themoviedb.presentation.ui.adapters.MoviePopularAdapter
import com.example.themoviedb.presentation.viewmodels.upcoming_fragment_viewmodel.UpcomingViewModel
import com.example.themoviedb.utils.gone
import com.example.themoviedb.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding: FragmentUpcomingBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private val upcomingViewModel: UpcomingViewModel by viewModels()


    private lateinit var moviePopularAdapter: MoviePopularAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        upcomingViewModel.initPopularMovie()

        initView()
        loadObservers()

    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        upcomingViewModel.popularMovie.observe(this, popularMovieObserver)
        upcomingViewModel.errorLiveData.observe(this, errorObserver)
        upcomingViewModel.progressLiveData.observe(this, progressObserver)

    }

    private val popularMovieObserver = Observer<List<MovieItem>> { movieList ->
        if (movieList != null) {
            moviePopularAdapter = MoviePopularAdapter()
            moviePopularAdapter.submitList(movieList)
            binding.upcomingMovieList.apply {
                adapter = moviePopularAdapter
            }
        }


    }

    private val progressObserver = Observer<Boolean> {
        val progressView = binding.upcomingProgressBar
        if (it) progressView.show()
        else progressView.gone()
    }

    private val errorObserver = Observer<Throwable> {
        if (it is Exception) {
//            handleException(it)
        } else {
//            makeErrorSnack(it.message.toString())
        }
    }


    private fun initView() {


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}