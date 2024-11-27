package com.takseha.danari.data.dto.circle


import com.google.gson.annotations.SerializedName

data class CircleFullInfo(
    @SerializedName("clubName")
    val clubName: String = "",
    @SerializedName("department")
    val department: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("events")
    val events: List<Event> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("recruitments")
    val recruitments: List<Recruitment> = listOf(),
    @SerializedName("reviews")
    val reviews: List<Review> = listOf(),
    @SerializedName("roomNumber")
    val roomNumber: String = ""
)