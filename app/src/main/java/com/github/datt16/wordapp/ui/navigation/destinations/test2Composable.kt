package com.github.datt16.wordapp.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.datt16.wordapp.SharedViewModel
import com.github.datt16.wordapp.ui.screens.auth.AuthScreen

fun NavGraphBuilder.test2Composable(
    sharedViewModel: SharedViewModel
) {
    composable(
        route = "auth"
    ) {
        AuthScreen(sharedViewModel = sharedViewModel)
    }
}