package com.takseha.danari.data.repository

import com.takseha.danari.data.api.ReviewService
import com.takseha.danari.data.dto.circle.ReviewRequest
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val client: ReviewService
) {
    suspend fun getReviewList(clubName: String) = client.getReviewList(clubName)
    suspend fun makeReview(request: ReviewRequest) = client.makeReview(request)
}