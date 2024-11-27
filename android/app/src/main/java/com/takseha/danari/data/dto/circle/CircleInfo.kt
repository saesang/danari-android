package com.takseha.danari.data.dto.circle


import com.google.gson.annotations.SerializedName

data class CircleInfo(
    @SerializedName("clubName")
    val clubName: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("roomNumber")
    val roomNumber: String
)