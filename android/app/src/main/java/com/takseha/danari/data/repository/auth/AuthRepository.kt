package com.takseha.danari.data.repository.auth

import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.dto.auth.RegisterRequest
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val client: AuthService
) {
    suspend fun registerUser(request: RegisterRequest) = client.registerUser(request)
}
