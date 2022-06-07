package com.github.datt16.wordapp

import androidx.lifecycle.ViewModel
import com.github.datt16.wordapp.models.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor() : ViewModel() {

    private val _allWords = MutableStateFlow<List<Word>>(emptyList())
    val allWords: StateFlow<List<Word>> = _allWords

    init {
        addWord(
            Word(
                "Hello",
                "こんにちは"
            )
        )
        addWord(
            Word(
                "Next",
                "次"
            )
        )
    }

    fun addWord(newWord: Word) {
        _allWords.value += newWord
    }
}
