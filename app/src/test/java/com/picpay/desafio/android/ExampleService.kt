package com.picpay.desafio.android

class ExampleService(
    private val service: com.example.data.network.api.UserService
) {

    fun example(): List<com.example.data.entity.UserNetworkEntity> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}