package com.example.themoviedb.presentation.ui.screens.actor_detail_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.response.ResponseActorCredits
import com.example.themoviedb.data.remote.response.ResponsePerson
import com.example.themoviedb.databinding.FragmentActorDetailBinding
import com.example.themoviedb.presentation.ui.adapters.MovieCreditAdapter
import com.example.themoviedb.presentation.viewmodels.actor_detail_viewmodel.ActorDetailViewModel
import com.example.themoviedb.utils.extensions.loadImageUrlWithBaseUrl
import com.example.themoviedb.utils.gone
import com.example.themoviedb.utils.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActorDetailFragment : Fragment() {

    private var _binding: FragmentActorDetailBinding? = null
    private val binding: FragmentActorDetailBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private val actorDetailViewModel: ActorDetailViewModel by viewModels()

    @Inject
    lateinit var storage: LocalStorage

    private var actorMovieCreditsAdapter = MovieCreditAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActorDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        actorDetailViewModel.getActorDetail(storage.actorId)
        actorDetailViewModel.getActorCredits(storage.actorId)


        initViews()
        loadObservers()
    }


    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        actorDetailViewModel.actorDetail.observe(this, actorDetailObserver)
        actorDetailViewModel.actorCredits.observe(this, actorCreditsObserver)
        actorDetailViewModel.errorLiveData.observe(this, errorObserver)
        actorDetailViewModel.progressLiveData.observe(this, progressObserver)

    }


    private val actorDetailObserver = Observer<ResponsePerson> { personResponse ->

        if (personResponse != null) {
            binding.apply {
                if (personResponse.profilePath != null) {
                    profileImage.loadImageUrlWithBaseUrl(personResponse.profilePath)
                }
                name.text = personResponse.name
                position.text = personResponse.knownForDepartment
                biography.text = personResponse.biography
            }
        }

    }

    private val actorCreditsObserver = Observer<ResponseActorCredits> { actorCreditsResponse ->

        if (actorCreditsResponse != null) {

            actorMovieCreditsAdapter.submitList(actorCreditsResponse.cast)
            binding.actorCreditList.apply {
                adapter = actorMovieCreditsAdapter
            }


        }
    }

    private val progressObserver = Observer<Boolean> {
        val progressView = binding.actorDetailProgressBar
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

        /*actorMovieCreditsAdapter.setOnClickListener {
            storage.actorId = it.id!!

        }*/

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        storage.actorId = -1
    }


}