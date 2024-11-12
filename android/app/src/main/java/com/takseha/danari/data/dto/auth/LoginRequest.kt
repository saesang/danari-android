package com.takseha.danari.data.dto.auth


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("password")
    val password: String
)