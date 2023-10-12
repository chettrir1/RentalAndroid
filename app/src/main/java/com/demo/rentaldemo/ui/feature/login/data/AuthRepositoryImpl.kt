package com.demo.rentaldemo.ui.feature.login.data

import com.demo.rentaldemo.ui.utils.SchedulersFactory

class AuthRepositoryImpl constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: AuthRepository.Local,
    private val remoteRepository: AuthRepository.Remote
) : AuthRepository {

}
