package com.yash.gitaverse.network

import com.yash.gitaverse.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}