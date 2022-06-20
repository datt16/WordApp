package com.github.datt16.wordapp.repository

import com.github.datt16.wordapp.models.UserData
import com.github.datt16.wordapp.models.Word
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@ViewModelScoped
class WordAppRepositoryImpl @Inject constructor() : WordAppRepository {

    private val sampleUserDataSet: UserData = UserData(
        id = "11112222",
        wordList = mapOf(
            "testList" to listOf(
                Word("Hello", "こんにちは", "ハロー"),
                Word("Good Bye", "さようなら", "シーユー"),
                Word("Eat", "食べる", "イート")
            ),
            "testList1" to listOf(
                Word("你好", "こんにちは", "nǐ hǎo"),
                Word("再见", "さようなら", "zài jiàn"),
                Word("吃", "食べる", "chī")
            )
        )
    )

    override fun getUserData(): Flow<UserData> {
        return flow<UserData> {
            // ここにリモートとローカルの切り分けを書く
            sampleUserDataSet
        }.flowOn(Dispatchers.IO)
    }
}
