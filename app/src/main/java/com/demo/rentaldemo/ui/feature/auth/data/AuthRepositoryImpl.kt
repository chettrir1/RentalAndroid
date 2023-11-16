package com.demo.rentaldemo.ui.feature.auth.data

import com.demo.rentaldemo.ui.feature.auth.data.local.AuthLocalImpl
import com.demo.rentaldemo.ui.feature.auth.data.model.CreateUserResponse
import com.demo.rentaldemo.ui.feature.auth.data.model.LoginResponse
import com.demo.rentaldemo.ui.feature.auth.data.remote.AuthRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl constructor(
    private val localRepository: AuthRepository.Local,
    private val remoteRepository: AuthRepository.Remote,
) : AuthRepository {

    companion object {
        @Volatile
        private var instance: AuthRepository? = null

        @Synchronized
        fun getInstance(): AuthRepository {
            if (instance != null) {
                return instance!!
            }

            val local = AuthLocalImpl.getInstance()
            val remote = AuthRemoteImpl.getInstance()
            return AuthRepositoryImpl(local, remote).also { instance = it }
        }
    }

    override suspend fun getData(): String {
        return withContext(Dispatchers.IO) {
            ""
        }
    }

    override suspend fun createUser(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
    ): CreateUserResponse {
        return withContext(Dispatchers.IO) {
            remoteRepository.createUser(firstName, lastName, email, phone, password)
        }
    }

    override suspend fun authenticateUser(email: String, password: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            remoteRepository.authenticateUser(email, password)
        }
    }

}
