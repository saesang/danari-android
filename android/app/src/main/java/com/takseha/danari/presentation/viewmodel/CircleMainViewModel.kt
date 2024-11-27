package com.takseha.danari.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takseha.danari.data.dto.circle.CircleFullInfo
import com.takseha.danari.data.dto.circle.Review
import com.takseha.danari.data.dto.circle.ReviewRequest
import com.takseha.danari.data.repository.CircleRepository
import com.takseha.danari.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CircleMainViewModel @Inject constructor(
    private val circleRepository: CircleRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(CircleFullInfo())
    val uiState = _uiState.asStateFlow()

    private var _reviewState = MutableStateFlow<List<Review>>(listOf())
    val reviewState = _reviewState.asStateFlow()

    fun getCircleFullInfo(clubName: String) = viewModelScope.launch {
        val response = circleRepository.getCircleFullInfo(clubName)

        if (response.isSuccessful) {
            _uiState.update {
                response.body() ?: CircleFullInfo()
            }
        } else {
            Log.e("CircleMainViewModel", "info response status: ${response.code()}\nresponse message: ${
                response.errorBody()?.string()
            }")
        }
    }

    fun getReviewList(clubName: String) = viewModelScope.launch {
        try {
            val response = reviewRepository.getReviewList(clubName)

            if (response.isSuccessful) {
                _reviewState.update {
                    response.body() ?: listOf()
                }
            } else {
                Log.e("CircleMainViewModel", "review response status: ${response.code()}\nresponse message: ${
                    response.errorBody()?.string()
                }")
            }
        } catch (e: Exception) {
            Log.e("CircleMainViewModel", "Error in getReviewList: ${e.message}")
        }
    }

    suspend fun makeReview(clubName: String, content: String) {
        try {
            val request = ReviewRequest(
                clubName = clubName,
                username = "mumalife",
                reviewContent = content
            )
            val response = reviewRepository.makeReview(request)

            if (response.isSuccessful) {
                try {
                    getReviewList(clubName)
                } catch (e: Exception) {
                    Log.e("CircleMainViewModel", "Error fetching review list: ${e.message}")
                }
            } else {
                Log.e("CircleMainViewModel", "make review response status: ${response.code()}\nresponse message: ${
                    response.errorBody()?.string()
                }")
            }
        } catch (e: Exception) {
            Log.e("CircleMainViewModel", "Error making review: ${e.message}")
        }
    }
}