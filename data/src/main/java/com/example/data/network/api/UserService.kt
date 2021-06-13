package com.example.data.network.api

import com.example.data.entity.UserNetworkEntity
import retrofit2.Response
import retrofit2.http.GET


interface UserService {


    @GET("users")
    suspend fun getUsers(): Response<List<UserNetworkEntity>>
}