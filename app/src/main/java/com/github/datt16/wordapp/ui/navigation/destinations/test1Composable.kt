package com.github.datt16.wordapp.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.datt16.wordapp.ui.screens.handson.HandsOnScreen1

fun NavGraphBuilder.test1Composable(navigateToAddScreen: () -> Unit) {
    composable(
        route = "test1"
    ) {
        HandsOnScreen1(navigateToAddScreen)
    }
}