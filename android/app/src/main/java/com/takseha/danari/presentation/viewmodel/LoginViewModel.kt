package com.takseha.danari.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.takseha.danari.data.api.AuthService
import com.takseha.danari.data.token.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val tokenManager: TokenManager,
    private val authService: AuthService
) : AndroidViewModel(application) {
    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    val userId = MutableStateFlow("")
    val password = MutableStateFlow("")


    fun onLoginClick() =viewModelScope.launch {
        val response = tokenManager.login(authService, userId.value, password.value)

        _loginState.value = response != null
    }
}