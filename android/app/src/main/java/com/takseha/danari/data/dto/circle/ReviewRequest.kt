package com.takseha.danari.data.dto.circle


import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("clubName")
    val clubName: String,
    @SerializedName("reviewContent")
    val reviewContent: String,
    @SerializedName("username")
    val username: String
)