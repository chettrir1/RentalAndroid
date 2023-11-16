package com.demo.rentaldemo.ui.feature.auth.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class LoginResponse(
    @SerializedName("access_token")
    var accessToken: String
)