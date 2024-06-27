package com.fee.aqarat.data.remote

import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/signup")
    suspend fun signUp(
        @Body signUpBody: SignUpBody
    ): SignUpResponse


}