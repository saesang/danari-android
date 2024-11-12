package com.takseha.danari.data.token

import android.content.Context
import android.util.Log
import com.takseha.danari.BuildConfig
import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.dto.auth.LoginRequest
import com.takseha.danari.data.dto.auth.LoginResponse
import com.takseha.danari.data.dto.auth.RegisterRequest
import com.takseha.danari.data.sharedPreferences.SP
import com.takseha.danari.data.sharedPreferences.SPKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TokenManager(context: Context) {
    private val prefs = SP(context)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.DANARI_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val loginApi = retrofit.create(AuthService::class.java)


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

    suspend fun login(userId: String, password: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(userId = userId, password = password)
                val response = loginApi.login(request)

                if (response.isSuccessful) {
                    accessToken = response.body()!!.accessToken
                    response.body()!!
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

    suspend fun registerUser(request: RegisterRequest): String? {
        return withContext(Dispatchers.IO) {
            try {
                val token = "Bearer $accessToken"
                val response = loginApi.registerUser(request)

                if (response.isSuccessful) {
                    response.body()!!
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