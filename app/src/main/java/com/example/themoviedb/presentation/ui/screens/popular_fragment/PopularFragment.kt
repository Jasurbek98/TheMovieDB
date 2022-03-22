package com.example.themoviedb.presentation.ui.screens.popular_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.databinding.FragmentPopularBinding
import com.example.themoviedb.presentation.ui.adapters.MoviePopularAdapter
import com.example.themoviedb.presentation.ui.screens.NavigationFragmentDirections
import com.example.themoviedb.presentation.viewmodels.popular_fragment_viewmodel.PopularViewModel
import com.example.themoviedb.utils.gone
import com.example.themoviedb.utils.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding: FragmentPopularBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private val popularViewModel: PopularViewModel by viewModels()

    @Inject
    lateinit var storage: LocalStorage


    private var moviePopularAdapter = MoviePopularAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        popularViewModel.initPopularMovie()

        initView()
        loadObservers()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        popularViewModel.popularMovie.observe(this, popularMovieObserver)
        popularViewModel.errorLiveData.observe(this, errorObserver)
        popularViewModel.progressLiveData.observe(this, progressObserver)

    }

    private val popularMovieObserver = Observer<List<MovieItem>> { movieList ->
//        Log.d("TTT", "$movieList")

        if (movieList != null) {
            moviePopularAdapter.submitList(movieList)
        }


    }

    private val progressObserver = Observer<Boolean> {
        val progressView = binding.popularProgressBar
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

        binding.movieList.apply {
            adapter = moviePopularAdapter
        }
        moviePopularAdapter.setOnClickListener {
            storage.movieId = it.id!!
            findNavController().navigate(NavigationFragmentDirections.actionNavigationFragmentToMovieDetailFragment())
        }

        moviePopularAdapter.fetchNextListener {
            popularViewModel.initPopularMovie()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}