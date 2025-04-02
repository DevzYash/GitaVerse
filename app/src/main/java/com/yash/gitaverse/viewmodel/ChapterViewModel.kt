package com.yash.gitaverse.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.gitaverse.model.Chapter
import com.yash.gitaverse.repository.ChapterRepository
import com.yash.gitaverse.utils.NetworkResult
import kotlinx.coroutines.launch

class ChapterViewModel(
    private val repository: ChapterRepository
) : ViewModel() {

    val chapters: MutableLiveData<NetworkResult<List<Chapter>>> = MutableLiveData()

    init {
        fetchChapters()
    }

    private fun fetchChapters() {
        viewModelScope.launch {
            chapters.postValue(NetworkResult.Loading())
            val response = repository.getChapters()
            if (response.isSuccessful) {
                response.body()?.let {
                    chapters.postValue(NetworkResult.Success(response.body()!!))
                }
            } else {
                chapters.postValue(NetworkResult.Error(response.message()))
            }
        }
    }


}