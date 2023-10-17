package com.demo.rentaldemo.ui.feature.auth.forgotpassword

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

class ForgotPasswordViewModel : ViewModel(),
    DefaultLifecycleObserver {

    private val repository by lazy { AuthRepositoryImpl.getInstance() }
    override fun onDestroy(owner: LifecycleOwner) {
        viewModelScope.cancel()
        super.onDestroy(owner)
    }



}