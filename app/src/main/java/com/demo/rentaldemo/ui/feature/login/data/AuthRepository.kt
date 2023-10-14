package com.demo.rentaldemo.ui.feature.login.data

interface AuthRepository {
    interface Local {
    }

    interface Remote {

    }

    suspend fun getData():String
}