package com.takseha.danari.data.repository.auth

import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.api.RetrofitInstance

class AuthRepository {
    private val client = RetrofitInstance.getInstance().create(AuthService::class.java)
}
