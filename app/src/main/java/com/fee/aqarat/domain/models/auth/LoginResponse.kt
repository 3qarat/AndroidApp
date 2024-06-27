package com.fee.aqarat.domain.models.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val loginResponseData: LoginResponseData = LoginResponseData(),
    val status: String = ""
)

data class LoginResponseData(
    val user: UserData = UserData()
)

data class UserData(
    val email: String = "",
    val id: Int = 0,
    val profile_picture: String? = "",
    val username: String = ""
)

data class LoginBody(
    val email: String,
    val password: String
)