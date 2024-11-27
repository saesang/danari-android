package com.takseha.danari.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takseha.danari.data.dto.circle.CircleInfo
import com.takseha.danari.data.dto.circle.CircleListResponse
import com.takseha.danari.data.repository.AuthRepository
import com.takseha.danari.data.repository.CircleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CircleListViewModel @Inject constructor(
    private val circleRepository: CircleRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(CircleListResponse())
    val uiState = _uiState.asStateFlow()

    private val _selectedBranchPosition = MutableStateFlow(0)
    val selectedBranchPosition: StateFlow<Int> = _selectedBranchPosition.asStateFlow()

    fun selectBranch(position: Int) {
        _selectedBranchPosition.value = position
    }

    fun getAllCircles() = viewModelScope.launch {
        val response = circleRepository.getAllCircles()

        if (response.isSuccessful) {
            _uiState.update {
                (response.body() ?: arrayListOf()) as CircleListResponse
            }
        } else {
            Log.e("CircleListViewModel", "response status: ${response.code()}\nresponse message: ${
                response.errorBody()?.string()
            }")
        }
    }

    fun getDepartmentCircles(department: String) = viewModelScope.launch {
        val response = circleRepository.getDepartmentCircles(department)

        if (response.isSuccessful) {
            _uiState.update {
                (response.body() ?: arrayListOf()) as CircleListResponse
            }
        } else {
            Log.e("CircleListViewModel", "response status: ${response.code()}\nresponse message: ${
                response.errorBody()?.string()
            }")
        }
    }
}