package com.takseha.danari.data.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.takseha.danari.data.token.TokenInterceptor
import com.takseha.danari.data.token.TokenManager
import com.takseha.danari.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private lateinit var tokenManager: TokenManager

    fun init(context: Context) {
        tokenManager = TokenManager(context)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(tokenManager))
            .build()
    }

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.DANARI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getInstance(): Retrofit {
        return retrofit
    }
}