package com.github.datt16.wordapp.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.datt16.wordapp.WordViewModel
import com.github.datt16.wordapp.ui.screens.list.WordListScreen
import com.github.datt16.wordapp.utils.Constants.WORD_LIST_SCREEN

fun NavGraphBuilder.wordListComposable(
    navigateToAddScreen: () -> Unit,
    wordViewModel: WordViewModel
) {
    composable(
        route = WORD_LIST_SCREEN
    ) {
        WordListScreen(navigateToAddScreen, wordViewModel)
    }
}