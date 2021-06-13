package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.UserRepositoryImpl

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepositoryImpl) as T
    }
}