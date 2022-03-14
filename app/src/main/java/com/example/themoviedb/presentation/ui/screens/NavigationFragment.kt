package com.example.themoviedb.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentNavigationBinding
import com.example.themoviedb.presentation.ui.adapters.ViewStateMovieAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationFragment : Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding: FragmentNavigationBinding
        get() = _binding ?: throw NullPointerException(getString(R.string.null_binding))


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()
    }


    private fun initView() {

        val viewPagerAdapter = ViewStateMovieAdapter(childFragmentManager, lifecycle)
        binding.vpFragments.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.vpFragments) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = requireActivity().getDrawable(R.drawable.ic_fire_solid)
                    tab.text = requireActivity().getText(R.string.popular)
                }
                1 -> {
                    tab.icon = requireActivity().getDrawable(R.drawable.ic_star_solid)
                    tab.text = requireActivity().getText(R.string.toprated)
                }
                2 -> {
                    tab.icon = requireActivity().getDrawable(R.drawable.ic_baseline_upcoming)
                    tab.text = requireActivity().getText(R.string.upcoming)

                }
            }
            binding.vpFragments.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}