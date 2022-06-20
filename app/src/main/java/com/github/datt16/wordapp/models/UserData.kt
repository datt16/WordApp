package com.github.datt16.wordapp.models

data class UserData(
    val id: String,
    val wordList: Map<String, List<Word>>
)
