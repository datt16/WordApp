package com.github.datt16.wordapp.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.datt16.wordapp.SharedViewModel
import com.github.datt16.wordapp.ui.components.UserCard

@Composable
fun AuthScreen(
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "auth screen") })
        }
    ) {
        SignInSampleSection(
            email = sharedViewModel.email,
            onSignInWithGoogleClicked = { /*TODO*/ },
            onSignInWithEmailClicked = { /*TODO*/ },
            onSignInWithEmailLinkClicked = { /*TODO*/ }) {
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