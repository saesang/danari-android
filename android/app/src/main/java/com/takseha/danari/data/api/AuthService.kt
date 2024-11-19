package com.takseha.danari.data.api

import com.takseha.danari.data.dto.auth.LoginRequest
import com.takseha.danari.data.dto.auth.TokenResponse
import com.takseha.danari.data.dto.auth.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<TokenResponse>

    @POST("/members/registration")
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): Response<String>
}