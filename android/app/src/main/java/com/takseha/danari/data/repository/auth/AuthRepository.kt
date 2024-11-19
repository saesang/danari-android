package com.takseha.danari.data.repository.auth

import com.takseha.danari.data.api.AuthService
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val client: AuthService
) {
//    suspend fun func1(request: RequestType) =
//        client.func1(request)
}
