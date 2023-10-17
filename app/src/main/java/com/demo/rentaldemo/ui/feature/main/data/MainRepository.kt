package com.demo.rentaldemo.ui.feature.main.data

interface MainRepository {
    interface Local {
    }

    interface Remote {

    }

    suspend fun getData():String
}