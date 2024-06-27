package com.fee.aqarat.data.remote

import com.fee.aqarat.domain.models.auth.LoginBody
import com.fee.aqarat.domain.models.auth.LoginResponse
import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/signup")
    suspend fun signUp(
        @Body signUpBody: SignUpBody
    ): SignUpResponse

    @POST("user/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse


}