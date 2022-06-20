package com.github.datt16.wordapp.repository

import com.github.datt16.wordapp.models.UserData
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WordAppRepository {
    fun getUserData(): Flow<Resource<UserData>>
    fun getPrivateWordList(): Flow<Resource<List<Word>>>
}