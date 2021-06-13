package com.example.data

import com.example.data.entity.UserNetworkEntity
import com.example.data.network.api.UserService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class UserServiceTest {

    private val api = mock<UserService>()


    @Test
    fun getUsersEmptyList() {

        val expectedUsers = emptyList<UserNetworkEntity>()

        whenever(api.getUsersResponse()).thenReturn(Response.success(expectedUsers))

        val users = api.getUsersResponse().body()

        assertEquals(users, expectedUsers)
    }
}