package com.picpay.desafio.android

import com.example.data.network.api.UserService

class ExampleService(
    private val service: com.example.data.network.api.UserService
) {

    fun example(): List<com.example.data.entity.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}