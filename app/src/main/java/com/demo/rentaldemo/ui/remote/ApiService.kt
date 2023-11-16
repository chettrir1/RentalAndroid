package com.demo.rentaldemo.ui.remote

import com.demo.rentaldemo.ui.feature.auth.data.model.CreateUserResponse
import com.demo.rentaldemo.ui.feature.auth.data.model.LoginResponse
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json")
    @POST("auth")
    suspend fun authenticateUser(@Body params: MutableMap<String, Any>): LoginResponse

    @Headers("Accept: application/json")
    @POST("users")
    suspend fun createUser(
        @Body requestParams: MutableMap<String, Any>,
    ): CreateUserResponse

    companion object {
        @Volatile
        private var apiService: ApiService? = null

        @Synchronized
        fun getInstance(): ApiService {
            if (apiService != null) {
                return apiService!!
            }
            val okHttpClient = OkHttpClient.getInstance()

            apiService = Retrofit.Builder()
                .baseUrl("http://4.206.218.72/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build().create(ApiService::class.java)

            return apiService!!
        }
    }
}