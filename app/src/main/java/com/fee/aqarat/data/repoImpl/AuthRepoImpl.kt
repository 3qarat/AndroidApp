package com.fee.aqarat.data.repoImpl

import com.fee.aqarat.data.remote.ApiService
import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse
import com.fee.aqarat.domain.repo.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepo {

    override suspend fun signUp(signUpBody: SignUpBody): SignUpResponse {
        return apiService.signUp(signUpBody)
    }


}