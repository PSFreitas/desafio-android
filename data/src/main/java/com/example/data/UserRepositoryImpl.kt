package com.example.data

import com.example.data.mapper.UserListMapper
import com.example.data.network.api.UserService
import com.example.domain.ResultData
import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApi: UserService,
    private val userListMapper: UserListMapper = UserListMapper()
) : UserRepository {

    override suspend fun getUsers(): ResultData<List<UserEntity>> {
        val response = userApi.getUsers()

        return if (response.isSuccessful) {
            ResultData.Success(userListMapper.map(response.body() ?: emptyList()))
        } else {
            ResultData.Error(Exception())
        }
    }
}