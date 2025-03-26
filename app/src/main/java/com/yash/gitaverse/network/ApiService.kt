package com.yash.gitaverse.network

import com.yash.gitaverse.model.Chapter
import com.yash.gitaverse.utils.Constants.API_HOST
import com.yash.gitaverse.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("chapters/?skip=0&limit=18")
    suspend fun getChapters(
        @Header("X-RapidAPI-Key") apiKey: String = API_KEY,
        @Header("X-RapidAPI-Host") apiHost: String = API_HOST
    ): Response<List<Chapter>>

}