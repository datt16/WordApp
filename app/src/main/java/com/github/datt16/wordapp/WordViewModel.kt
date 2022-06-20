package com.github.datt16.wordapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.repository.WordAppRepository
import com.github.datt16.wordapp.utils.Resource
import com.github.datt16.wordapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val wordAppRepository: WordAppRepository
) : ViewModel() {

    private val _allWords = MutableStateFlow<List<Word>>(emptyList())
    val allWords: StateFlow<List<Word>> = _allWords

    var loading = MutableStateFlow(false)
        private set


    init {
        viewModelScope.launch {
            wordAppRepository.getPrivateWordList().collect {
                when(it.Status) {
                    Status.LOADING -> {
                        loading.value = true
                    }
                    Status.SUCCESS -> {
                        loading.value = false
                        _allWords.value = it.data!!
                    }
                    else -> {}
                }
            }
        }
    }

    fun getWordList(listName: String): List<Word> {
//        val list = sampleUserDataSet.wordList[listName]
//        return if (list.isNullOrEmpty()) emptyList()
//        else list
        return emptyList()
    }

    fun addWord(newWord: Word) {
        _allWords.value += newWord
    }
}


