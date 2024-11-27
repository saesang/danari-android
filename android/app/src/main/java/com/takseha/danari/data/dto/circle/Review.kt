package com.takseha.danari.data.dto.circle


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("clubName")
    val clubName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("reviewContent")
    val reviewContent: String,
    @SerializedName("username")
    val username: String
)