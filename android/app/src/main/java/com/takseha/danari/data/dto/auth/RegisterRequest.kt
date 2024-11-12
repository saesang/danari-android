package com.takseha.danari.data.dto.auth


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("membershipRegistrationDTOList")
    val userClubList: List<UserClub>,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("studentId")
    val studentId: Int,
    @SerializedName("username")
    val userName: String
)

data class UserClub(
    @SerializedName("certificateImageUrls")
    val certificateImageUrls: String,
    @SerializedName("clubName")
    val clubName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String
)