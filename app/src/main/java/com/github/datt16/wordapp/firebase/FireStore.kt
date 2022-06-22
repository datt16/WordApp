package com.github.datt16.wordapp.firebase

import android.util.Log
import com.github.datt16.wordapp.models.UserData
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.Flow

// 多分いらん
fun getFireStoreInstance(): FirebaseFirestore {
    return Firebase.firestore
}

fun getPrivateWordList(uid: String) {
    val db = Firebase.firestore

    db.collectionGroup("wordList")
        .get()
        .addOnSuccessListener { result ->

            Log.d("Firebase/firestore", "${result.documents}")
            // 格納する

        }
        .addOnFailureListener { exception ->
            val errorMessage = exception.message

            Log.w("Firebase/firestore", "Error getting documents", exception)
            Log.e("Firebase/firestore", errorMessage!!)

        }
}

fun addNewWord(word: Word) {
    val db = Firebase.firestore

    db.collection("allWords")
        .add(word)
        .addOnSuccessListener {

        }
        .addOnFailureListener { exception ->
            val errorMessage = exception.message

            Log.w("Firebase/firestore", "Error getting documents", exception)
            Log.e("Firebase/firestore", errorMessage!!)

        }
}

@ExperimentalCoroutinesApi
fun getWords() = callbackFlow {
    val doc = Firebase.firestore.collection("allWords")

    val callback = doc.addSnapshotListener { value, error ->
        val result = if (error != null) {
            Resource.error(error.message, null)
        } else {
            Resource.success(value?.toObjects<Word>())
        }

        this.trySend(result).isSuccess
    }

    awaitClose {
        callback.remove()
    }
}

fun getAllWords() {
    val db = Firebase.firestore


    db.collection("allWords")
        .get()
        .addOnSuccessListener {
            it.toObjects<List<Word>>()

        }
        .addOnFailureListener { exception ->
            val errorMessage = exception.message

            Log.w("Firebase/firestore", "Error getting documents", exception)
            Log.e("Firebase/firestore", errorMessage!!)

        }
}