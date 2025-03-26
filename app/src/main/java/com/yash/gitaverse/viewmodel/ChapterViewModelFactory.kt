package com.yash.gitaverse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yash.gitaverse.repository.ChapterRepository

class ChapterViewModelFactory(private val repository: ChapterRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChapterViewModel(repository) as T
    }
}