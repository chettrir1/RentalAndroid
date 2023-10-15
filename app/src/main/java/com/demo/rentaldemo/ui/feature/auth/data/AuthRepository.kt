package com.demo.rentaldemo.ui.feature.auth.data

interface AuthRepository {
    interface Local {
    }

    interface Remote {

    }

    suspend fun getData():String
}