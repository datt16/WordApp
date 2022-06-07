package com.github.datt16.wordapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WordCard(word: String, mean: String, read: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Column() {
                    Text(
                        text = word,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    when {
                        !read.isNullOrEmpty() -> {
                            Text(
                                text = read,
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .alpha(0.4f),
                    text = mean,
                    style = MaterialTheme.typography.body2
                )
            }
//            Row(
//                modifier = Modifier.weight(1f),
//                horizontalArrangement = Arrangement.End
//            ) {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        imageVector = Icons.Filled.Menu,
//                        contentDescription = "",
//                        tint = MaterialTheme.colors.onSurface
//                    )
//                }
//            }

        }
    }
}

@Preview
@Composable
fun WordCardPreview() {
    WordCard(word = "Hello", mean = "こんにちは", read = "ハロー")
}