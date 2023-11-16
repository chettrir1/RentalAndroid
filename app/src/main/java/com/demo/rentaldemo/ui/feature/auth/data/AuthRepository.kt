package com.demo.rentaldemo.ui.feature.auth.data

import com.demo.rentaldemo.ui.feature.auth.data.model.CreateUserResponse
import com.demo.rentaldemo.ui.feature.auth.data.model.LoginResponse

interface AuthRepository {
    interface Local {
    }

    interface Remote {
        suspend fun createUser(
            firstName: String,
            lastName: String,
            email: String,
            phone: String,
            password: String,
        ): CreateUserResponse

        suspend fun authenticateUser(email: String, password: String): LoginResponse

    }

    suspend fun getData(): String
    suspend fun createUser(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
    ): CreateUserResponse

    suspend fun authenticateUser(email: String, password: String): LoginResponse
}