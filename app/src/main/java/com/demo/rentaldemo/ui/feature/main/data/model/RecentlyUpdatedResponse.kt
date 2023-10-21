package com.demo.rentaldemo.ui.feature.main.data.model

data class RecentlyUpdatedResponse(
    var id: Int,
    var title: String,
    var image: String,
    var address: String,
    var roomCount: String,
    var currency_code: String,
    var price: String,
    var price_type: String,
    var status: String,
)
