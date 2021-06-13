package com.picpay.desafio.android.mapper

import com.example.data.mapper.Mapper
import com.example.domain.entity.UserEntity
import com.picpay.desafio.android.entity.UserViewEntity

class UserViewEntityMapper : Mapper<List<UserEntity>, List<UserViewEntity>> {
    override fun map(input: List<UserEntity>): List<UserViewEntity> {
        val userViewEntityList = mutableListOf<UserViewEntity>()

        for (user in input) {
            userViewEntityList.add(
                UserViewEntity(
                    id = user.id,
                    name = user.name,
                    username = user.username,
                    image = user.image
                )
            )
        }
        return userViewEntityList
    }

    override fun reverseMap(input: List<UserViewEntity>): List<UserEntity> {
        val userEntityList = mutableListOf<UserEntity>()

        for (user in input) {
            userEntityList.add(
                UserEntity(
                    id = user.id,
                    image = user.image,
                    username = user.username,
                    name = user.name
                )
            )
        }
        return userEntityList

    }
}