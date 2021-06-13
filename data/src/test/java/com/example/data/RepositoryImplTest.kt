package com.example.data

import com.example.data.entity.UserNetworkEntity
import com.example.data.network.api.UserService
import com.example.domain.ResultData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    private val api = mock<UserService>()
    private val repository = UserRepositoryImpl(api)

    @Test
    fun `should return success with empty list when body is null`() {
        runBlocking {

            whenever(api.getUsers()).thenReturn(Response.success(null))
            val success = repository.getUsers() as ResultData.Success

            assertNotNull(success)
            assertTrue(success.data.isEmpty())
        }
    }

    @Test
    fun `should return success with empty list when body is empty`() {
        runBlocking {

            whenever(api.getUsers()).thenReturn(Response.success(emptyList()))
            val success = repository.getUsers() as ResultData.Success

            assertNotNull(success)
            assertTrue(success.data.isEmpty())
        }
    }

    @Test
    fun `should return success with filled list when body is not empty`() {
        runBlocking {

            whenever(api.getUsers()).thenReturn(
                Response.success(
                    listOf(
                        UserNetworkEntity(
                            id = 0,
                            img = "data/image/jpg1",
                            username = "Nic Holder",
                            name = "Nicholas Holder"
                        )
                    )
                )
            )
            val success = repository.getUsers() as ResultData.Success

            assertTrue(success.data.size == 1)
        }
    }

    @Test
    fun `should return error if request fails`() {
        runBlocking {
            whenever(api.getUsers()).thenReturn(
                Response.error(
                    404,
                    "{ \"error\": 404 }".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

            val error = repository.getUsers() as ResultData.Error
            val exception = error.exception

            assertNotNull("error should not be null", error)
            assertNotNull("exception should not be null", exception)

        }
    }
}