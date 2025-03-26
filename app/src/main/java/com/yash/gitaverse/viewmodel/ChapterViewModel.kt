package com.yash.gitaverse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.gitaverse.model.Chapter
import com.yash.gitaverse.repository.ChapterRepository
import kotlinx.coroutines.launch

class ChapterViewModel(
    private val repository: ChapterRepository
) : ViewModel() {

    val chapters: LiveData<List<Chapter>> get() = repository.chapters

    fun fetchChapters() {
        viewModelScope.launch {
            repository.getChapters()
        }
    }

}