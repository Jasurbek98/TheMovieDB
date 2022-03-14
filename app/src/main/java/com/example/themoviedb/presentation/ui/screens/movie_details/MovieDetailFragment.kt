package com.example.themoviedb.presentation.ui.screens.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.data.remote.response.ResponseActors
import com.example.themoviedb.data.remote.response.ResponseMovieDetails
import com.example.themoviedb.databinding.FragmentMovieDetailBinding
import com.example.themoviedb.databinding.FragmentPopularBinding
import com.example.themoviedb.presentation.ui.adapters.MoviePopularAdapter
import com.example.themoviedb.presentation.ui.adapters.PeopleAdapter
import com.example.themoviedb.presentation.ui.screens.NavigationFragmentDirections
import com.example.themoviedb.presentation.viewmodels.movie_detail_viewmodel.MovieDetailViewModel
import com.example.themoviedb.presentation.viewmodels.popular_fragment_viewmodel.PopularViewModel
import com.example.themoviedb.utils.extensions.loadImageUrlWithBaseUrl
import com.example.themoviedb.utils.gone
import com.example.themoviedb.utils.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private var peopleAdapter = PeopleAdapter()


    @Inject
    lateinit var storage: LocalStorage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieDetailViewModel.initMovieActors(storage.movieId)
        movieDetailViewModel.initMovieDetail(storage.movieId)


        initViews()
        loadObservers()
    }


    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        movieDetailViewModel.movieDetail.observe(this, popularMovieObserver)
        movieDetailViewModel.actors.observe(this, movieActorsObserver)
        movieDetailViewModel.errorLiveData.observe(this, errorObserver)
        movieDetailViewModel.progressLiveData.observe(this, progressObserver)

    }

    @SuppressLint("SetTextI18n")
    private val popularMovieObserver = Observer<ResponseMovieDetails> { detailsMovie ->
        if (detailsMovie != null) {
            binding.apply {
                movieDetailPhoto.loadImageUrlWithBaseUrl(detailsMovie.backdropPath!!)
                movieTitle.text = detailsMovie.title
                textCreationCountryGenreAdults.text =
                    detailsMovie.releaseDate + ", " + detailsMovie.productionCountries?.get(0)?.name + ", " + detailsMovie.genres?.get(
                        0
                    )?.name
                overview.text = detailsMovie.overview
                langs.text = detailsMovie.originalLanguage
            }

        }
    }

    private val movieActorsObserver = Observer<ResponseActors> { actorsResponse ->
        Log.d("LLLL","${actorsResponse.toString()}")
        if (actorsResponse != null) {
            peopleAdapter.submitList(actorsResponse.cast)
            binding.actorsList.apply {
                adapter = peopleAdapter
            }
        }
    }

    private val progressObserver = Observer<Boolean> {
        val progressView = binding.detailProgressBar
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


    private fun initViews() {

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        storage.movieId = -1
    }

}