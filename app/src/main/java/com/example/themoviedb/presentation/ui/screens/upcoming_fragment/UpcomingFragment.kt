package com.example.themoviedb.presentation.ui.screens.upcoming_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentPopularBinding
import com.example.themoviedb.databinding.FragmentTopRatedBinding
import com.example.themoviedb.databinding.FragmentUpcomingBinding


class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding: FragmentUpcomingBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}