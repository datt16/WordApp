package com.github.datt16.wordapp.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.datt16.wordapp.SharedViewModel
import com.github.datt16.wordapp.ui.screens.auth.AuthScreen
import com.github.datt16.wordapp.utils.Constants.AUTH_SCREEN

fun NavGraphBuilder.authComposable(
    sharedViewModel: SharedViewModel
) {
    composable(
        route = AUTH_SCREEN
    ) {
        AuthScreen(sharedViewModel = sharedViewModel)
    }
}
