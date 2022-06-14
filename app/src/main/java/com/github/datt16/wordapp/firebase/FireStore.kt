package com.github.datt16.wordapp.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// 多分いらん
fun getFireStoreInstance(): FirebaseFirestore {
    return Firebase.firestore
}

fun getUserData(uid: String) {
    val db = Firebase.firestore
    db.collection("users").document(uid).get()
        .addOnSuccessListener { result ->
            Log.d("Firebase/firestore", "$result")

            // 格納する

        }
        .addOnFailureListener { exception ->
            val errorMessage = exception.message

            Log.w("Firebase/firestore", "Error getting documents", exception)
            Log.e("Firebase/firestore", errorMessage!!)

        }
}