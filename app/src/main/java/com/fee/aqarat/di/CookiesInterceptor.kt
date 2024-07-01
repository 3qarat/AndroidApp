package com.fee.aqarat.di

import android.content.SharedPreferences
import okhttp3.*

class CookiesInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val cookies = sharedPreferences.getStringSet("cookies", emptySet()) ?: emptySet()

        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie)
        }

        return chain.proceed(builder.build())
    }
}

class MyCookieJar(private val sharedPreferences: SharedPreferences) : CookieJar {

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val cookiesSet = cookies.map { it.toString() }.toSet()
        sharedPreferences.edit().putStringSet("cookies", cookiesSet).apply()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = sharedPreferences.getStringSet("cookies", emptySet()) ?: emptySet()
        return cookies.map { Cookie.parse(url, it)!! }
    }
}
