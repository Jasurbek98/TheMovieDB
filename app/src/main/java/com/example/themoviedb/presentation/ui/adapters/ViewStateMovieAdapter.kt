package com.example.themoviedb.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themoviedb.presentation.ui.screens.popular_fragment.PopularFragment
import com.example.themoviedb.presentation.ui.screens.top_rated_fragment.TopRatedFragment
import com.example.themoviedb.presentation.ui.screens.upcoming_fragment.UpcomingFragment

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
class ViewStateMovieAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList =
        arrayListOf(
            PopularFragment(),
            TopRatedFragment(),
            UpcomingFragment(),
        )

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}