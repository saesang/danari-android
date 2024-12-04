package com.takseha.danari.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.takseha.danari.BuildConfig
import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.api.CircleService
import com.takseha.danari.data.token.TokenInterceptor
import com.takseha.danari.data.token.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.DANARI_BASE_URL)
            .client(OkHttpClient.Builder()
                .build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @DefaultRetrofit
    fun provideDefaultRetrofit(tokenManager: TokenManager, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.DANARI_BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor(tokenManager))
                .build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(@AuthRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideCircleService(@DefaultRetrofit retrofit: Retrofit): CircleService {
        return retrofit.create(CircleService::class.java)
    }
}
