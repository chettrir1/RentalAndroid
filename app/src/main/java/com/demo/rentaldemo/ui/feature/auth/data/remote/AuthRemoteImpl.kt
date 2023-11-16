package com.demo.rentaldemo.ui.feature.auth.data.remote

import com.demo.rentaldemo.ui.feature.auth.data.AuthRepository
import com.demo.rentaldemo.ui.feature.auth.data.model.CreateUserResponse
import com.demo.rentaldemo.ui.feature.auth.data.model.LoginResponse
import com.demo.rentaldemo.ui.remote.ApiService

class AuthRemoteImpl private constructor() : AuthRepository.Remote {

    private val apiService by lazy {
        ApiService.getInstance()
    }

    companion object {
        @Volatile
        private var instance: AuthRepository.Remote? = null

        @Synchronized
        fun getInstance(): AuthRepository.Remote {
            if (instance != null) {
                return instance!!
            }
            return AuthRemoteImpl().also { instance = it }
        }
    }

    override suspend fun createUser(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
    ): CreateUserResponse {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["firstname"] = firstName
        requestParams["lastname"] = lastName
        requestParams["email"] = email
        requestParams["phonenumber"] = phone
        requestParams["password"] = password
        return apiService.createUser(requestParams)
    }

    override suspend fun authenticateUser(email: String, password: String): LoginResponse {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["email"] = email
        requestParams["password"] = password
        return apiService.authenticateUser(requestParams)
    }


}