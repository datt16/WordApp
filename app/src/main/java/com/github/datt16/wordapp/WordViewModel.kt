package com.github.datt16.wordapp

import androidx.lifecycle.ViewModel
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.repository.WordAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val wordAppRepository: WordAppRepository
) : ViewModel() {

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


