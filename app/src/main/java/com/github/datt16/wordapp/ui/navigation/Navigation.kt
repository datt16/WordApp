package com.github.datt16.wordapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.datt16.wordapp.SharedViewModel
import com.github.datt16.wordapp.WordViewModel
import com.github.datt16.wordapp.ui.navigation.destinations.wordListComposable
import com.github.datt16.wordapp.ui.navigation.destinations.authComposable
import com.github.datt16.wordapp.utils.Constants.WORD_LIST_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    wordViewModel: WordViewModel
) {
    val screen = remember(navController) {
        Screens(navController)
    }

    NavHost(navController = navController, startDestination = WORD_LIST_SCREEN) {

        wordListComposable(
            screen.auth,
            wordViewModel
        )

        authComposable(
            sharedViewModel
        )
    }
}