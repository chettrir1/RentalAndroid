package com.demo.rentaldemo.ui.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface SchedulersFactory {
    fun ui(): MainCoroutineDispatcher
    fun io(): CoroutineDispatcher
}