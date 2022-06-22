package com.github.datt16.wordapp.repository

import com.github.datt16.wordapp.firebase.getWords
import com.github.datt16.wordapp.models.UserData
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.utils.Resource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WordAppRepositoryImpl @Inject constructor() : WordAppRepository {

    private val sampleUserDataSet: UserData = UserData(
        id = "11112222",
        wordList = mapOf(
            "testList" to listOf(
                Word(wordTitle = "Hello", wordMean = "こんにちは", wordRead = "ハロー"),
                Word(wordTitle = "Good Bye", wordMean = "さようなら", wordRead = "シーユー"),
                Word(wordTitle = "Eat", wordMean = "食べる", wordRead = "イート")
            ),
            "testList1" to listOf(
                Word(wordTitle = "你好", wordMean = "こんにちは", wordRead = "nǐ hǎo"),
                Word(wordTitle = "再见", wordMean = "さようなら", wordRead = "zài jiàn"),
                Word(wordTitle = "吃", wordMean = "食べる", wordRead = "chī")
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

//    override fun _getPrivateWordList(): Flow<Resource<List<Word>>> {
//        return flow<Resource<List<Word>>> {
//
//            // ここにリモートとローカルの切り分け
//            emit(Resource.loading())
//            try {
//                // getUserData ... Firebase上のデータをとってくる
//                emit(getAllWords())
//
//                // サンプルデータを代入してる
//                emit(Resource.success(sampleUserDataSet.wordList.getValue("testList1")))
//            } catch (exception: Exception) {
//                emit(Resource.error(exception.message))
//            }
//
//        }.flowOn(Dispatchers.IO)
//    }

    @ExperimentalCoroutinesApi
    override fun getPrivateWordList(): Flow<Resource<List<Word>>> {
        return callbackFlow {
            val doc = Firebase.firestore.collection("allWords")

            this.trySend(Resource.loading())

            val localOnly = true
            if (localOnly) {
                this.trySend(Resource.success(sampleUserDataSet.wordList.getValue("testList1")))
            }

            val callback = doc.addSnapshotListener { value, error ->

                val result = when {
                    // エラー発生時
                    error != null -> {
                        Resource.error(error.message, null)
                    }
                    // ワードリストが空だった時
                    value == null -> {
                        Resource.success(emptyList<Word>())
                    }
                    // 成功時
                    else -> {
                        Resource.success(value.toObjects<Word>())
                    }
                }

                this.trySend(result).isSuccess
            }

            awaitClose {
                callback.remove()
            }
        }.flowOn(Dispatchers.IO)
    }
}
