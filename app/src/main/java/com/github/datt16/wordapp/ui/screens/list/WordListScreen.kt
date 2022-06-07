package com.github.datt16.wordapp.ui.screens.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.datt16.wordapp.WordViewModel
import com.github.datt16.wordapp.models.Word
import com.github.datt16.wordapp.ui.components.WordCard

@Composable
fun WordListScreen(
    navigateToAddScreen: () -> Unit,
    wordViewModel: WordViewModel
) {
    val allWords by wordViewModel.allWords.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "単語帳",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            )
        },
        content = {
            WordListDisplay(words = allWords)
        },
        floatingActionButton = {
            ListFab(
                onFabClicked = {
                    navigateToAddScreen()
                }
            )
        }
    )
}

@Composable
fun ListFab(onFabClicked: () -> Unit) {
    FloatingActionButton(
        onClick = { onFabClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "単語を追加"
        )
    }
}

@Composable
fun WordListDisplay(
    words: List<Word>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(words) { word ->
            WordCard(
                word = word.wordTitle,
                mean = word.wordMean,
                read = word.wordRead
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}