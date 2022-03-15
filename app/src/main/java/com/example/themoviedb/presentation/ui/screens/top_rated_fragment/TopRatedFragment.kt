package com.example.themoviedb.presentation.ui.screens.top_rated_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.databinding.FragmentTopRatedBinding
import com.example.themoviedb.presentation.ui.adapters.MoviePopularAdapter
import com.example.themoviedb.presentation.ui.screens.NavigationFragmentDirections
import com.example.themoviedb.presentation.viewmodels.top_rated_fragment_viewmodel.TopRatedViewModel
import com.example.themoviedb.utils.gone
import com.example.themoviedb.utils.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private var _binding: FragmentTopRatedBinding? = null
    private val binding: FragmentTopRatedBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private val topRatedViewModel: TopRatedViewModel by viewModels()


    private var moviePopularAdapter = MoviePopularAdapter()

    @Inject
    lateinit var storage: LocalStorage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        topRatedViewModel.initPopularMovie()

        initView()
        loadObservers()

    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        topRatedViewModel.popularMovie.observe(this, popularMovieObserver)
        topRatedViewModel.errorLiveData.observe(this, errorObserver)
        topRatedViewModel.progressLiveData.observe(this, progressObserver)

    }

    private val popularMovieObserver = Observer<List<MovieItem>> { movieList ->
        if (movieList != null) {
            moviePopularAdapter.submitList(movieList)
            binding.topRatedMovieList.apply {
                adapter = moviePopularAdapter
            }
        }


    }

    private val progressObserver = Observer<Boolean> {
        val progressView = binding.topRatedProgressBar
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
        moviePopularAdapter.setOnClickListener {
            storage.movieId = it.id!!
            findNavController().navigate(NavigationFragmentDirections.actionNavigationFragmentToMovieDetailFragment())
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}