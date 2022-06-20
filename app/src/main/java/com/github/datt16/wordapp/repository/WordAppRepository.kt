package com.github.datt16.wordapp.repository

import com.github.datt16.wordapp.models.UserData
import kotlinx.coroutines.flow.Flow

interface WordAppRepository {
    fun getUserData(): Flow<UserData>
}