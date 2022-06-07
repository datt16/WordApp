package com.github.datt16.wordapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.datt16.wordapp.SharedViewModel
import com.github.datt16.wordapp.ui.navigation.destinations.test1Composable
import com.github.datt16.wordapp.ui.navigation.destinations.test2Composable

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController)
    }

    NavHost(navController = navController, startDestination = "test1") {

        test1Composable(
            screen.test2
        )

        test2Composable(
            sharedViewModel
        )
    }
}