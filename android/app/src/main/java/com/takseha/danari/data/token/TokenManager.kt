package com.takseha.danari.data.token

import android.content.Context
import android.util.Log
import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.dto.auth.LoginRequest
import com.takseha.danari.data.dto.auth.RegisterRequest
import com.takseha.danari.data.dto.auth.TokenResponse
import com.takseha.danari.data.sharedPreferences.SP
import com.takseha.danari.data.sharedPreferences.SPKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = SP(context)

    var accessToken: String?
        get() = prefs.loadPref(SPKey.ACCESS_TOKEN, "")
        set(value) {
            prefs.savePref(SPKey.ACCESS_TOKEN, value!!)
        }

    var refreshToken: String?
        get() = prefs.loadPref(SPKey.REFRESH_TOKEN, "")
        set(value) {
            prefs.savePref(SPKey.REFRESH_TOKEN, value!!)
        }

    suspend fun login(authService: AuthService, userId: String, password: String): TokenResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(userId = userId, password = password)
                val response = authService.login(request)

                if (response.isSuccessful) {
                    response.body()?.let {
                        accessToken = it.accessToken
                        refreshToken = it.refreshToken
                        it
                    }
                } else {
                    Log.e(
                        "TokenManager",
                        "login response status: ${response.code()}\nlogin response message: ${
                            response.errorBody()?.string()
                        }"
                    )
                    null
                }
            } catch (e: Exception) {
                Log.e("TokenManager", e.message.toString())
                throw e
            }
        }
    }

    suspend fun registerUser(authService: AuthService, request: RegisterRequest): String? {
        return withContext(Dispatchers.IO) {
            try {
                val token = "Bearer $accessToken"
                val response = authService.registerUser(request)

                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e(
                        "TokenManager",
                        "register response status: ${response.code()}\nregister response message: ${
                            response.errorBody()?.string()
                        }"
                    )
                    null
                }
            } catch (e: Exception) {
                Log.e("TokenManager", "register error: ${e.message}")
                throw e
            }
        }
    }
}