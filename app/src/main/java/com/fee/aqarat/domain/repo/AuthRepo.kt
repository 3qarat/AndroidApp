package com.fee.aqarat.domain.repo

import com.fee.aqarat.domain.models.auth.LoginBody
import com.fee.aqarat.domain.models.auth.LoginResponse
import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse

interface AuthRepo {

    suspend fun signUp(signUpBody: SignUpBody): SignUpResponse

    suspend fun login(loginBody: LoginBody): LoginResponse


}