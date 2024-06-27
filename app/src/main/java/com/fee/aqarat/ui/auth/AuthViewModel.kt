package com.fee.aqarat.ui.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fee.aqarat.domain.models.auth.LoginBody
import com.fee.aqarat.domain.models.auth.LoginResponse
import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse
import com.fee.aqarat.domain.repo.AuthRepo
import com.fee.aqarat.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
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


    private val _login = MutableStateFlow<Resource<LoginResponse>>(Resource.idle)
    val loginFlow: Flow<Resource<LoginResponse>> = _login

    fun login(loginBody: LoginBody) {
        viewModelScope.launch(Dispatchers.IO) {
            _login.emit(Resource.Loading)
            try {
                val response = authRepo.login(loginBody)
                _login.emit(Resource.Success(response))
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    500 -> "بريد أو كلمة مرورغير صحيحة"
                    else -> "Network error: ${e.message}"
                }
                _login.emit(Resource.Error(IOException(errorMessage)))
            } catch (e: SocketTimeoutException) {
                _login.emit(Resource.Error(IOException("Timeout error: ${e.message}")))
            } catch (e: IOException) {
                _login.emit(Resource.Error(IOException("IO error: ${e.message}")))
            } catch (e: Exception) {
                _login.emit(Resource.Error(IOException("Unknown error: ${e.message}")))
            }
        }
    }


}