package com.example.domain.repository

import com.example.domain.ResultData
import com.example.domain.entity.UserEntity

interface UserRepository {
    suspend fun getUsers(): ResultData<List<UserEntity>>
}