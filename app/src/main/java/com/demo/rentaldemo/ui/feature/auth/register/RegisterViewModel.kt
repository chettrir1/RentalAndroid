package com.demo.rentaldemo.ui.feature.auth.register

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.rentaldemo.ui.feature.auth.data.AuthRepositoryImpl
import com.demo.rentaldemo.ui.feature.auth.data.model.CreateUserResponse
import com.demo.rentaldemo.ui.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(),
    DefaultLifecycleObserver {

    private val repository by lazy { AuthRepositoryImpl.getInstance() }

    private val registerUseCase = MutableLiveData<Response<CreateUserResponse>>()
    val registerResponse: LiveData<Response<CreateUserResponse>> =
        registerUseCase

    override fun onDestroy(owner: LifecycleOwner) {
        viewModelScope.cancel()
        super.onDestroy(owner)
    }

    fun createUser(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
    ) {
        viewModelScope.launch {
            registerUseCase.value = Response.loading()
            try {
                registerUseCase.value = Response.complete(
                    repository.createUser(firstName, lastName, email, phone, password)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                registerUseCase.value = Response.error(error)
            }
        }

    }
}