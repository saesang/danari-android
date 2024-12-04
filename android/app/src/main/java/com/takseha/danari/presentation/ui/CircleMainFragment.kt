package com.takseha.danari.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.Event
import com.takseha.danari.data.dto.circle.Recruitment
import com.takseha.danari.data.dto.circle.Review
import com.takseha.danari.databinding.FragmentCircleListBinding
import com.takseha.danari.databinding.FragmentCircleMainBinding
import com.takseha.danari.presentation.adapter.CategoryRVAdapter
import com.takseha.danari.presentation.adapter.CircleRVAdapter
import com.takseha.danari.presentation.adapter.EventRVAdapter
import com.takseha.danari.presentation.adapter.RecruitRVAdapter
import com.takseha.danari.presentation.adapter.ReviewRVAdapter
import com.takseha.danari.presentation.viewmodel.CircleListViewModel
import com.takseha.danari.presentation.viewmodel.CircleMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CircleMainFragment : Fragment() {
    private var _binding: FragmentCircleMainBinding? = null
    private val binding get() = _binding!!
    private val viewmodel : CircleMainViewModel by viewModels()
    private lateinit var clubName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubName = requireActivity().intent.getStringExtra("clubName") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCircleMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            backBtn.setOnClickListener {
                requireActivity().finish()
            }

            viewmodel.getCircleFullInfo(clubName)
            viewLifecycleOwner.lifecycleScope.launch {
                viewmodel.uiState.collectLatest {
                    circleName.text = it.clubName
                    circleDesc.text = it.description
                    circleLocation.text = it.roomNumber

                    setEventList(it.events)
                    setRecruitList(it.recruitments)
                    setReviewList(it.reviews)
                }
            }
        }
    }

    private fun setEventList(
        events: List<Event>
    ) {
        with(binding) {
            val eventRVAdapter =
                EventRVAdapter(requireContext(), events)
            eventList.adapter = eventRVAdapter
            eventList.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setRecruitList(
        recruits: List<Recruitment>
    ) {
        with(binding) {
            val recruitRVAdapter =
                RecruitRVAdapter(requireContext(), recruits)

            recruitList.adapter = recruitRVAdapter
            recruitList.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setReviewList(
        reviews: List<Review>
    ) {
        with(binding) {
            val reviewRVAdapter =
                ReviewRVAdapter(requireContext(), reviews)

            reviewList.adapter = reviewRVAdapter
            reviewList.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}