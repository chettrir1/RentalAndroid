package com.demo.rentaldemo.ui.feature.login.data.remote

import com.demo.rentaldemo.ui.feature.login.data.AuthRepository
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


}