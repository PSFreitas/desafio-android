package com.example.data

import com.example.data.entity.UserNetworkEntity
import com.example.data.network.api.UserService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class UserServiceTest {

    private val api = mock<UserService>()


    @Test
    fun getUsersEmptyList() {

        val expectedUsers = emptyList<UserNetworkEntity>()

        runBlocking {
            whenever(api.getUsers()).thenReturn(Response.success(expectedUsers))

            val users = api.getUsers().body()

            assertEquals(users, expectedUsers)
        }
    }
}