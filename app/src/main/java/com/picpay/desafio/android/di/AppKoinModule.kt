package com.picpay.desafio.android.di

import com.example.data.UserRepositoryImpl
import com.example.data.mapper.UserListMapper
import com.example.data.network.api.UserClient
import com.example.domain.repository.UserRepository
import com.picpay.desafio.android.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appKoinModule = module {
    factory { MainViewModel(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single { UserClient(androidContext()).userService }
    single { UserListMapper() }
}