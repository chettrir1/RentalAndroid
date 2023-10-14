package com.demo.rentaldemo.ui.feature.login.data

import com.demo.rentaldemo.ui.feature.login.data.local.AuthLocalImpl
import com.demo.rentaldemo.ui.feature.login.data.remote.AuthRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl constructor(
    private val localRepository: AuthRepository.Local,
    private val remoteRepository: AuthRepository.Remote
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

}
