package com.fee.aqarat.domain.repo

import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse

interface AuthRepo {

    suspend fun signUp(signUpBody: SignUpBody): SignUpResponse

}