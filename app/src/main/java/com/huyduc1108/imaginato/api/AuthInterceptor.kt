package com.huyduc1108.imaginato.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("IMSI", "357175048449937")
            .addHeader("IMEI", "510110406068589")
            .build()
        return chain.proceed(request)
    }
}
