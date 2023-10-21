package com.demo.rentaldemo.ui.feature.main.data.remote

import com.demo.rentaldemo.ui.feature.main.data.MainRepository
import com.demo.rentaldemo.ui.feature.main.data.model.CategoryResponse
import com.demo.rentaldemo.ui.feature.main.data.model.RecentlyUpdatedResponse
import com.demo.rentaldemo.ui.remote.ApiService

class MainRemoteImpl private constructor() : MainRepository.Remote {

    private val apiService by lazy {
        ApiService.getInstance()
    }

    companion object {
        @Volatile
        private var instance: MainRepository.Remote? = null

        @Synchronized
        fun getInstance(): MainRepository.Remote {
            if (instance != null) {
                return instance!!
            }
            return MainRemoteImpl().also { instance = it }
        }
    }

    override suspend fun getCategories(): List<CategoryResponse> {
        val items = mutableListOf<CategoryResponse>()
        items.add(
            CategoryResponse(
                id = 1,
                title = "Houses",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                searchCount = "2041"
            )
        )
        items.add(
            CategoryResponse(
                id = 2,
                title = "Condos",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                searchCount = "2041"
            )
        )
        items.add(
            CategoryResponse(
                id = 3,
                title = "Houses",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                searchCount = "2041"
            )
        )
        items.add(
            CategoryResponse(
                id = 4,
                title = "Houses",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                searchCount = "2041"
            )
        )

        return items
    }

    override suspend fun getRecentlyUpdatedResponse(): List<RecentlyUpdatedResponse> {
        val items = mutableListOf<RecentlyUpdatedResponse>()
        items.add(
            RecentlyUpdatedResponse(
                id = 1,
                title = "Small nature friendly house",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                address = "Owen street, Barrie",
                roomCount = "2",
                price = "2400",
                currency_code = "$",
                price_type = "month",
                status = "Available"
            )
        )

        items.add(
            RecentlyUpdatedResponse(
                id = 2,
                title = "Apartment with great sea view",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                address = "Owen street, Barrie",
                roomCount = "2",
                price = "5260",
                currency_code = "$",
                price_type = "month",
                status = "Booked"
            )
        )
        items.add(
            RecentlyUpdatedResponse(
                id = 2,
                title = "Countryside home",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                address = "Owen street, Barrie",
                roomCount = "2",
                price = "5260",
                currency_code = "$",
                price_type = "month",
                status = "Booked"
            )
        )
        items.add(
            RecentlyUpdatedResponse(
                id = 1,
                title = "Small nature friendly house",
                image = "https://source.unsplash.com/user/c_v_r/1900x800",
                address = "Owen street, Barrie",
                roomCount = "2",
                price = "2400",
                currency_code = "$",
                price_type = "month",
                status = "Available"
            )
        )

        return items
    }


}