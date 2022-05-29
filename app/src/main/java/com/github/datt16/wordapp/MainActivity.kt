package com.github.datt16.wordapp

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.datt16.wordapp.ui.theme.WordAppTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private lateinit var signInRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true
    private lateinit var oneTapClient: SignInClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            ).build()


        setContent {
            WordAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "SignUp")
                            }
                        )
                    },
                    content = {
                        val email = remember {
                            mutableStateOf("")
                        }

                        Column() {

                            Button(
                                onClick = {
                                    Log.d(
                                        "DEBUG",
                                        Firebase.auth.currentUser?.email.toString()
                                    )
                                }) {
                                Text(text = "リロード")
                            }

                            SignInSampleSection(
                                email = "",
                                onSignInWithGoogleClicked = {
                                    oneTapClient.beginSignIn(signInRequest)
                                        .addOnSuccessListener { result ->
                                            try {
                                                Log.d("TAG", "Google-SignIn Started")
                                                startIntentSenderForResult(
                                                    result.pendingIntent.intentSender,
                                                    REQ_ONE_TAP,
                                                    null,
                                                    0,
                                                    0,
                                                    0,
                                                    null
                                                )
                                                when {
                                                    Firebase.auth.currentUser?.email.isNullOrEmpty() -> {
                                                        email.value = ""
                                                    }
                                                    else -> {
                                                        email.value =
                                                            Firebase.auth.currentUser?.email!!
                                                    }
                                                }
                                            } catch (e: IntentSender.SendIntentException) {
                                                Log.e(
                                                    TAG,
                                                    "Couldn't start One Tap UI: ${e.localizedMessage}"
                                                )
                                            }
                                        }.addOnFailureListener { e ->
                                            // No saved credentials found. Launch the One Tap sign-up flow, or
                                            // do nothing and continue presenting the signed-out UI.
                                            e.localizedMessage?.let { it1 -> Log.d(TAG, it1) }
                                        }.addOnCompleteListener {
                                            Log.d(
                                                "DEBUG",
                                                Firebase.auth.currentUser?.email.toString()
                                            )
                                        }

                                },
                                onSignInWithEmailClicked = { },
                                onSignInWithEmailLinkClicked = { },
                                onSignOutClicked = {
                                    Firebase.auth.signOut()
                                    email.value = ""
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        Log.d("auth", currentUser.toString())
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val username = credential.id
                    val password = credential.password

                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with your backend.

                            val firebaseCredential =
                                GoogleAuthProvider.getCredential(idToken, null)

                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "signInWithCredential:success")
                                        val user = auth.currentUser
                                        Log.d("Firebase/auth", user?.uid.toString())
                                    } else {
                                        Log.w(
                                            TAG,
                                            "signInWithCredential:failure",
                                            task.exception
                                        )
                                    }
                                }
                        }
                        password != null -> {
                            // Got a saved username and password. Use them to authenticate
                            // with your backend.
                            Log.d(TAG, "Got password.")
                        }
                        else -> {
                            // Shouldn't happen.
                            Log.d(TAG, "No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Log.d(TAG, "One-tap dialog was closed.")
                            // Don't re-prompt the user.
                            showOneTapUI = false
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun UserCard(email: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Column(Modifier.padding(16.dp)) {

            when {
                email.isEmpty() -> {
                    Text(text = "ログインしていません", style = MaterialTheme.typography.subtitle1)
                    Text(text = "下記ボタンからログインしてください", style = MaterialTheme.typography.body2)
                }
                else -> {
                    Text(text = "ログイン済み", style = MaterialTheme.typography.subtitle1)
                    Text(text = email, style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Composable
fun SignInSampleSection(
    email: String,
    onSignInWithGoogleClicked: () -> Unit,
    onSignInWithEmailClicked: () -> Unit,
    onSignInWithEmailLinkClicked: () -> Unit,
    onSignOutClicked: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
    ) {
        Row {
            UserCard(email)
        }

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "サインイン",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(end = 8.dp)
            )
            Divider(modifier = Modifier.fillMaxWidth())
        }

        Row(Modifier.padding(horizontal = 16.dp)) {
            Button(
                onClick = { onSignInWithGoogleClicked() }
            ) {
                Text(text = "Google")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { onSignInWithEmailLinkClicked() }) {
                Text(text = "メールリンク")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { onSignInWithEmailClicked() }) {
                Text(text = "メール")
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "サインアウト",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(end = 8.dp)
            )
            Divider(modifier = Modifier.fillMaxWidth())
        }

        Row(Modifier.padding(horizontal = 16.dp)) {
            Button(onClick = { onSignOutClicked() }) {
                Text(text = "サインアウト")
            }
        }


    }

}

@Composable
@Preview(showBackground = true)
fun Preview() {
    WordAppTheme {
        SignInSampleSection(
            email = "aaa@gmail.com",
            onSignInWithEmailClicked = {},
            onSignInWithEmailLinkClicked = {},
            onSignOutClicked = {},
            onSignInWithGoogleClicked = {}
        )
    }
}


