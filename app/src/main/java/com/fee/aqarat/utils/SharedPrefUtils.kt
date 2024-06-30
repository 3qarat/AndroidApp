package com.fee.aqarat.utils

import android.content.Context
import android.content.SharedPreferences


object SharedPrefUtils {

    fun rememberMe(
        context: Context,
        email: String?,
        pass: String?
    ) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("AuthSharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("pass", pass)
        editor.apply()
    }

    fun getEmail(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("AuthSharedPref", Context.MODE_PRIVATE)
        return sharedPreferences.getString(
            "email",
            ""
        )
    }

    fun getPassword(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("AuthSharedPref", Context.MODE_PRIVATE)
        return sharedPreferences.getString(
            "pass",
            ""
        )
    }


}