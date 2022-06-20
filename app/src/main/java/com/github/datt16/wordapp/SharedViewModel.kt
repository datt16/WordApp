package com.github.datt16.wordapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.datt16.wordapp.models.UserData
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.repository.WordAppRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val wordAppRepository: WordAppRepository
) : ViewModel() {

    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData

    var email: String by mutableStateOf("")
        private set

    // 形だけのサインイン
    fun signIn(mail: String) {
        this.email = mail
    }

    fun signOut() {
        Firebase.auth.signOut()
        this.email = ""
    }

}