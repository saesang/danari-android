package com.takseha.danari.data.api

import com.takseha.danari.data.dto.circle.ReviewRequest
import com.takseha.danari.data.dto.circle.ReviewResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewService {
    @GET("/reviews/list/{clubName}")
    suspend fun getReviewList(
        @Path("clubName") clubName: String
    ): Response<ReviewResponse>

    @POST("/reviews")
    suspend fun makeReview(
        @Body request: ReviewRequest
    ): Response<String>
}