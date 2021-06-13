package com.example.data.network.api

import com.example.data.entity.User
import retrofit2.Call
import retrofit2.http.GET


interface UserService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}