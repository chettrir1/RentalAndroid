package com.demo.rentaldemo.ui.feature.main.data

import com.demo.rentaldemo.ui.feature.main.data.model.CategoryResponse
import com.demo.rentaldemo.ui.feature.main.data.model.RecentlyUpdatedResponse

interface MainRepository {
    interface Local {
    }

    interface Remote {
        suspend fun getCategories(): List<CategoryResponse>
        suspend fun getRecentlyUpdatedResponse(): List<RecentlyUpdatedResponse>

    }

    suspend fun getCategories(): List<CategoryResponse>
    suspend fun getRecentlyUpdatedResponse(): List<RecentlyUpdatedResponse>
}