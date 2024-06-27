package com.fee.aqarat.ui.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse
import com.fee.aqarat.domain.repo.AuthRepo
import com.fee.aqarat.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _signUp = MutableStateFlow<Resource<SignUpResponse>>(Resource.idle)
    val signUpFlow: Flow<Resource<SignUpResponse>> = _signUp

    fun signUp(signUpBody: SignUpBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _signUp.emit(
                    Resource.Success(
                        authRepo.signUp(signUpBody)
                    )
                )
            } catch (e: Exception) {
                _signUp.emit(
                    Resource.Error(e)
                )
            }
        }
    }

}