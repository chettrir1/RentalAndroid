package com.demo.rentaldemo.ui.feature.auth.data.local

import com.demo.rentaldemo.ui.feature.auth.data.AuthRepository
import com.demo.rentaldemo.ui.feature.main.data.MainRepository
import com.demo.rentaldemo.ui.feature.main.data.local.MainLocalImpl

class AuthLocalImpl private constructor() : AuthRepository.Local {

    companion object {
        @Volatile
        private var instance: AuthRepository.Local? = null
        @Synchronized
        fun getInstance(): AuthRepository.Local {
            if (instance != null) {
                return instance!!
            }
            return AuthLocalImpl().also { instance = it }
        }
    }

}
