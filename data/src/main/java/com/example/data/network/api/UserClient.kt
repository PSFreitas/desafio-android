package com.example.data.network.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserClient(
    context: Context
) {

    private val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    private val cacheSize = (5.times(1024).times(1024)).toLong()
    private val cache = Cache(context.cacheDir, cacheSize)

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                chain.proceed(chain.request()).newBuilder()
                    .header("Cache-Control", "public, max-age=" + 5).build()
            }
            .build()
    }

    val userService: UserService by lazy { retrofit.create(UserService::class.java) }


}