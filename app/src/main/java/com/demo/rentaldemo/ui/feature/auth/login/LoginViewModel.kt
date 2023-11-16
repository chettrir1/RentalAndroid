package com.demo.rentaldemo.ui.feature.auth.login

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.rentaldemo.ui.feature.auth.data.AuthRepositoryImpl
import com.demo.rentaldemo.ui.feature.auth.data.model.LoginResponse
import com.demo.rentaldemo.ui.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(),
    DefaultLifecycleObserver {

    private val repository by lazy { AuthRepositoryImpl.getInstance() }

    private val loginUseCase = MutableLiveData<Response<LoginResponse>>()
    val loginResponse: LiveData<Response<LoginResponse>> =
        loginUseCase


    override fun onDestroy(owner: LifecycleOwner) {
        viewModelScope.cancel()
        super.onDestroy(owner)
    }

    fun authenticateUser(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            loginUseCase.value = Response.loading()
            try {
                loginUseCase.value = Response.complete(
                    repository.authenticateUser(email, password)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                loginUseCase.value = Response.error(error)
            }
        }

    }


}