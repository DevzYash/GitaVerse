package com.yash.gitaverse.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yash.gitaverse.model.Chapter
import com.yash.gitaverse.network.ApiService
import com.yash.gitaverse.utils.Constants.TAG

class ChapterRepository(private val apiService: ApiService) {

    private val _chapters = MutableLiveData<List<Chapter>>()
    val chapters: LiveData<List<Chapter>> get() = _chapters

    suspend fun getChapters() {
        try {
            val response = apiService.getChapters()
            if (response.isSuccessful && response.body() != null) {
                _chapters.postValue(response.body())
            } else {
                _chapters.postValue(emptyList())
            }
        } catch (e: Exception) {
            _chapters.postValue(emptyList())
            Log.e(TAG, "getChapters: error at ${e.message} ")
        }
    }

}