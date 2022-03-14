package com.example.themoviedb.presentation.ui.screens.popular_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.databinding.FragmentPopularBinding
import com.example.themoviedb.presentation.ui.adapters.MoviePopularAdapter


class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding: FragmentPopularBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private lateinit var moviePopularAdapter: MoviePopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        val list = listOf(
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),
            MovieItem(),

            )
        moviePopularAdapter = MoviePopularAdapter()
        moviePopularAdapter.submitList(list)
        binding.movieList.apply {
            adapter = moviePopularAdapter
//            layoutManager = GridLayoutManager(requireContext(), 4)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}