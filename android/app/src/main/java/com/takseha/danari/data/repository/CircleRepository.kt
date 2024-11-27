package com.takseha.danari.data.repository

import com.takseha.danari.data.api.CircleService
import javax.inject.Inject

class CircleRepository @Inject constructor(
    private val client: CircleService
) {
    suspend fun getAllCircles() = client.getAllCircles()
    suspend fun getDepartmentCircles(department: String) = client.getDepartmentCircles(department)
    suspend fun getCircleFullInfo(clubName: String) = client.getCircleFullInfo(clubName)
}