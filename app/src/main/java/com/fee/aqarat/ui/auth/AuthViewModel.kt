package com.fee.aqarat.ui.auth


import androidx.lifecycle.ViewModel
import com.fee.aqarat.domain.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

//    private val _register = MutableStateFlow<Resource<Slider>>(Resource.idle)
//    val registerFlow: Flow<Resource<Slider>> = _register
//
//    fun register() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                _register.emit(
//                    Resource.Success(
//                        authRepo.register()
//                    )
//                )
//            } catch (e: Exception) {
//                _register.emit(
//                    Resource.Error(e)
//                )
//            }
//        }
//    }

}