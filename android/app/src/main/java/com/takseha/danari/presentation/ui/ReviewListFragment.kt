package com.takseha.danari.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takseha.danari.data.dto.circle.Review
import com.takseha.danari.databinding.FragmentReviewListBinding
import com.takseha.danari.presentation.adapter.ReviewRVAdapter
import com.takseha.danari.presentation.viewmodel.CircleMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewListFragment : Fragment() {
    private var _binding: FragmentReviewListBinding? = null
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
        _binding = FragmentReviewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewmodel.getReviewList(clubName)
            viewLifecycleOwner.lifecycleScope.launch {
                viewmodel.reviewState.collectLatest {
                    setReviewList(it)
                }
            }

            backBtn.setOnClickListener {
                it.findNavController().popBackStack()
            }

            postBtn.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewmodel.makeReview(clubName, reviewEditText.text.toString())
                }
                reviewEditText.setText("")
            }
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