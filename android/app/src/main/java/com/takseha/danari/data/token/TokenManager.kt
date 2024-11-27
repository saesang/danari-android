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
}