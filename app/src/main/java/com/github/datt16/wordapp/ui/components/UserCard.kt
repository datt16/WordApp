package com.github.datt16.wordapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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