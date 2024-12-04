package com.takseha.danari.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takseha.danari.data.dto.circle.CircleFullInfo
import com.takseha.danari.data.dto.circle.CircleListResponse
import com.takseha.danari.data.repository.CircleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CircleMainViewModel @Inject constructor(
    private val circleRepository: CircleRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(CircleFullInfo())
    val uiState = _uiState.asStateFlow()

    fun getCircleFullInfo(clubName: String) = viewModelScope.launch {
        val response = circleRepository.getCircleFullInfo(clubName)

        if (response.isSuccessful) {
            _uiState.update {
                response.body() ?: CircleFullInfo()
            }
        } else {
            Log.e("CircleMainViewModel", "response status: ${response.code()}\nresponse message: ${
                response.errorBody()?.string()
            }")
        }
    }
}