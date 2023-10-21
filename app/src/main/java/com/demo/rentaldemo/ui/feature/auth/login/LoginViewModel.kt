package com.demo.rentaldemo.ui.feature.auth.login

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.rentaldemo.ui.feature.auth.data.AuthRepositoryImpl
import kotlinx.coroutines.cancel

class LoginViewModel : ViewModel(),
    DefaultLifecycleObserver {

    private val repository by lazy { AuthRepositoryImpl.getInstance() }

    override fun onDestroy(owner: LifecycleOwner) {
        viewModelScope.cancel()
        super.onDestroy(owner)
    }



}