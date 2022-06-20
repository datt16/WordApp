package com.github.datt16.wordapp.repository

import com.github.datt16.wordapp.models.UserData
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.utils.Resource
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

    override fun getUserData(): Flow<Resource<UserData>> {
        return flow<Resource<UserData>> {
            // ここにリモートとローカルの切り分け
            emit(Resource.loading())
            try {
                // getUserData ... Firebase上のデータをとってくる

                // サンプルデータを代入してる
                emit(Resource.success(sampleUserDataSet))
            } catch (exception: Exception) {
                emit(Resource.error(exception.message))
            }

        }.flowOn(Dispatchers.IO)
    }

    override fun getPrivateWordList(): Flow<Resource<List<Word>>> {
        return flow<Resource<List<Word>>> {

            // ここにリモートとローカルの切り分け
            emit(Resource.loading())
            try {
                // getUserData ... Firebase上のデータをとってくる

                // サンプルデータを代入してる
                emit(Resource.success(sampleUserDataSet.wordList.getValue("testList1")))
            } catch (exception: Exception) {
                emit(Resource.error(exception.message))
            }

        }.flowOn(Dispatchers.IO)
    }
}
