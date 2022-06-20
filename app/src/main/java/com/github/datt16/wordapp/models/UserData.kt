package com.github.datt16.wordapp.models

data class UserData(
    val id: String = "0",
    val wordList: Map<String, List<Word>> = emptyMap()
)
