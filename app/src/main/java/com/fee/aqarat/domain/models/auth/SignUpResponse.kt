package com.fee.aqarat.domain.models.auth

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    val status: String,
    val data: SignUpResponseData
)

data class SignUpResponseData(
    @SerializedName("user_id")
    val userID: Int
)

data class SignUpBody(
    val username: String,
    val email: String,
    val password: String,
    @SerializedName("mobile_num")
    val mobileNum: List<String>
)

