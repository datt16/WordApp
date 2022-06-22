package com.github.datt16.wordapp.models

import com.google.firebase.firestore.DocumentId

data class Word(
    @DocumentId
    val id: String = "",
    val lang: String = "ja",

    /**
     * 単語の名前
     */
    val wordTitle: String,

    /**
     * 単語の意味
     */
    val wordMean: String,

    /**
     * 単語の読み方
     */
    val wordRead: String = ""
)
