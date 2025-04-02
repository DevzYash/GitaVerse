package com.yash.gitaverse.repository

import com.yash.gitaverse.network.ApiService

class ChapterRepository(private val apiService: ApiService) {

    suspend fun getChapters() = apiService.getChapters()

}