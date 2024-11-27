package com.takseha.danari.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takseha.danari.R
import com.takseha.danari.data.dto.auth.RegisterRequest
import com.takseha.danari.data.dto.auth.Role
import com.takseha.danari.data.dto.auth.UserClub
import com.takseha.danari.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _registerState = MutableStateFlow<Boolean?>(null)
    val registerState: StateFlow<Boolean?> = _registerState

    val name = MutableStateFlow("")
    val studentId = MutableStateFlow("")
    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    val inClub = MutableStateFlow<Boolean>(false)
    val userClubList = arrayListOf<UserClub>()
    val branch = MutableStateFlow("")
    val clubNames = MutableStateFlow(R.array.dropdown_liberalArts)
    val clubName = MutableStateFlow("")
    val role = MutableStateFlow<Role?>(null)
    val certImageUrl = MutableStateFlow("")


    fun onInClubCheckedChanged(checkedId: Int) {
        inClub.value = (checkedId == R.id.inBtn)
    }

    fun onBranchSelected(selectedBranch: String) {
        branch.value = selectedBranch
        clubNames.value = when (selectedBranch) {
            "교양분과" -> R.array.dropdown_liberalArts
            "봉사분과" -> R.array.dropdown_volunteer
            "학술분과" -> R.array.dropdown_academic
            "공연예술분과" -> R.array.dropdown_performingArts
            "문예창작분과" -> R.array.dropdown_literaryCreation
            "종교분과" -> R.array.dropdown_religious
            "체육분과" -> R.array.dropdown_sports
            else -> R.array.dropdown_volunteer
        }
    }

    fun onClubNameSelected(selectedClub: String) {
        clubName.value = selectedClub
    }

    fun onRoleCheckedChanged(checkedId: Int) {
        if (checkedId == R.id.leaderBtn) {
            role.value = Role.PRESIDENT
        } else {
            role.value = Role.MEMBER
        }
        userClubList.add(UserClub(
            name = name.value,
            clubName = clubName.value,
            role = role.value.toString(),
            certificateImageUrls = ""
        ))
    }

    fun onRegisterClick() = viewModelScope.launch {
        val request = RegisterRequest(
            name = name.value,
            studentId = studentId.value.toInt(),
            userId = id.value,
            password = password.value,
            userClubList = userClubList
        )
        Log.d("RegisterViewModel", request.toString())
        val response = authRepository.registerUser(request)
        if (response.isSuccessful) {
            _registerState.value = true
            Log.d("RegisterViewModel", "회원가입 성공!")
        } else {
            _registerState.value = false
            Log.e("RegisterViewModel", "response status: ${response.code()}\nresponse message: ${
                response.errorBody()?.string()
            }")
        }
    }
}