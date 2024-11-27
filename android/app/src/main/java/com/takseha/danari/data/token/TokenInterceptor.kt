package com.takseha.danari.data.token

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = addTokenToRequest(request, tokenManager.accessToken)
        val response: Response

        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return response
    }

    private fun addTokenToRequest(request: Request, token: String?): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
    }
}