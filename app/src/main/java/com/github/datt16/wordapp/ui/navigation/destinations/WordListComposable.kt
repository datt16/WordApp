package com.github.datt16.wordapp.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.datt16.wordapp.ui.screens.handson.HandsOnScreen1
import com.github.datt16.wordapp.utils.Constants.WORD_LIST_SCREEN

fun NavGraphBuilder.wordListComposable(navigateToAddScreen: () -> Unit) {
    composable(
        route = WORD_LIST_SCREEN
    ) {
        HandsOnScreen1(navigateToAddScreen)
    }
}