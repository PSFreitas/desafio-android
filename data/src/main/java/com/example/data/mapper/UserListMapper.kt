package com.example.data.mapper

import com.example.data.entity.UserNetworkEntity
import com.example.domain.entity.UserEntity

class UserListMapper : Mapper<List<UserNetworkEntity>, List<UserEntity>> {
    override fun map(input: List<UserNetworkEntity>): List<UserEntity> {
        val userEntities = mutableListOf<UserEntity>()

        for (userNetworkEntity in input) {
            userEntities.add(
                UserEntity(
                    image = userNetworkEntity.img,
                    username = userNetworkEntity.username,
                    id = userNetworkEntity.id,
                    name = userNetworkEntity.name
                )
            )
        }

        return userEntities

    }

    override fun reverseMap(input: List<UserEntity>): List<UserNetworkEntity> {
        val userNetworkEntities = mutableListOf<UserNetworkEntity>()

        for (userEntity in input) {
            userNetworkEntities.add(
                UserNetworkEntity(
                    id = userEntity.id,
                    name = userEntity.name,
                    username = userEntity.username,
                    img = userEntity.image
                )
            )
        }

        return userNetworkEntities
    }


}