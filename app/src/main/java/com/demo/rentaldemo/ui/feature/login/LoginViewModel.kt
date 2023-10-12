package com.demo.rentaldemo.ui.feature.login

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.rentaldemo.ui.feature.login.data.AuthRepository
import kotlinx.coroutines.cancel

class LoginViewModel constructor(repository: AuthRepository) : ViewModel(),
    DefaultLifecycleObserver {

    override fun onDestroy(owner: LifecycleOwner) {
        viewModelScope.cancel()
        super.onDestroy(owner)
    }

}