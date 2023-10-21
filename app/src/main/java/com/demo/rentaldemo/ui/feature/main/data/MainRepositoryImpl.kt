package com.demo.rentaldemo.ui.feature.main.data

import com.demo.rentaldemo.ui.feature.main.data.local.MainLocalImpl
import com.demo.rentaldemo.ui.feature.main.data.model.CategoryResponse
import com.demo.rentaldemo.ui.feature.main.data.model.RecentlyUpdatedResponse
import com.demo.rentaldemo.ui.feature.main.data.remote.MainRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl constructor(
    private val localRepository: MainRepository.Local,
    private val remoteRepository: MainRepository.Remote
) : MainRepository {

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        @Synchronized
        fun getInstance(): MainRepository {
            if (instance != null) {
                return instance!!
            }

            val local = MainLocalImpl.getInstance()
            val remote = MainRemoteImpl.getInstance()
            return MainRepositoryImpl(local, remote).also { instance = it }
        }
    }

    override suspend fun getCategories(): List<CategoryResponse> {
        return withContext(Dispatchers.IO) {
            remoteRepository.getCategories()
        }
    }

    override suspend fun getRecentlyUpdatedResponse(): List<RecentlyUpdatedResponse> {
        return withContext(Dispatchers.IO) {
            remoteRepository.getRecentlyUpdatedResponse()
        }
    }

}
