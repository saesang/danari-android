package com.takseha.danari.data.dto.circle


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("clubName")
    val clubName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("postContent")
    val postContent: String,
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("postTitle")
    val postTitle: String,
    @SerializedName("postType")
    val postType: String,
    @SerializedName("username")
    val username: String
)