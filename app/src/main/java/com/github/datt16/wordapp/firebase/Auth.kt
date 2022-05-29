package com.github.datt16.wordapp.firebase

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

const val TAG = "Firebase/Auth"

fun signUp(
    email: String,
    pass: String,
    onSuccess: (AuthResult) -> Unit,
    onFailure: (Exception) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    auth.createUserWithEmailAndPassword(email, pass)
        .addOnSuccessListener {
            onSuccess(it)
        }.addOnFailureListener { e ->
            onFailure(e)
            Log.e(TAG, e.toString())
        }
}

fun signInWithEmail(
    email: String,
    pass: String,
    onSuccess: (AuthResult) -> Unit,
    onFailure: (Exception) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    auth.signInWithEmailAndPassword(email, pass)
        .addOnSuccessListener {
            onSuccess(it)
        }
        .addOnFailureListener { e ->
            onFailure(e)
            Log.e(TAG, e.toString())
        }
}