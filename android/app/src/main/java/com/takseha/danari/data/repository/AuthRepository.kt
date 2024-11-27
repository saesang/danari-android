package com.takseha.danari.data.repository

import android.util.Log
import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.dto.auth.LoginRequest
import com.takseha.danari.data.dto.auth.RegisterRequest
import com.takseha.danari.data.dto.auth.TokenResponse
import com.takseha.danari.data.token.TokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val client: AuthService,
    private val tokenManager: TokenManager
) {
    suspend fun login(userId: String, password: String): TokenResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(userId = userId, password = password)
                val response = client.login(request)

                if (response.isSuccessful) {
                    response.body()?.let {
                        tokenManager.accessToken = it.accessToken
                        tokenManager.refreshToken = it.refreshToken
                        Log.d("AuthRepository", it.accessToken)
                        it
                    }
                } else {
                    Log.e(
                        "AuthRepository",
                        "login response status: ${response.code()}\nlogin response message: ${
                            response.errorBody()?.string()
                        }"
                    )
                    null
                }
            } catch (e: Exception) {
                Log.e("AuthRepository", e.message.toString())
                throw e
            }
        }
    }

    suspend fun registerUser(request: RegisterRequest) = client.registerUser(request)
}
