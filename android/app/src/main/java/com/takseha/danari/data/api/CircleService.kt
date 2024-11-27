package com.takseha.danari.data.api

import com.takseha.danari.data.dto.circle.CircleFullInfo
import com.takseha.danari.data.dto.circle.CircleListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CircleService {
    @GET("/clubs/list/all")
    suspend fun getAllCircles(
    ): Response<CircleListResponse>

    @GET("/clubs/list/{department}")
    suspend fun getDepartmentCircles(
        @Path("department") department: String
    ): Response<CircleListResponse>

    @GET("/clubs/details/{clubName}")
    suspend fun getCircleFullInfo(
        @Path("clubName") clubName: String
    ): Response<CircleFullInfo>
}