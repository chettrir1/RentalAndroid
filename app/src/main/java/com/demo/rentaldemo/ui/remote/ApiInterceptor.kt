package com.demo.rentaldemo.ui.remote


import android.content.Context
import com.demo.rentaldemo.ui.utils.NetworkNotAvailableException
import com.demo.rentaldemo.ui.utils.checkNetworkAvailability
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ApiInterceptor constructor(
    val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!checkNetworkAvailability(context)) {
            throw NetworkNotAvailableException("No Internet Connection.")
        }
        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
        val responseBody = response.body
        val responseString = responseBody?.string()

        when (response.code) {
            else -> return response.newBuilder()
                .body((responseString ?: "").toResponseBody())
                .build()
        }
    }
}