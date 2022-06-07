package com.github.datt16.wordapp.ui.navigation

import androidx.navigation.NavController
import com.github.datt16.wordapp.utils.Constants.AUTH_SCREEN
import com.github.datt16.wordapp.utils.Constants.WORD_LIST_SCREEN

class Screens(navController: NavController) {
    val list: () -> Unit = {
        navController.navigate(WORD_LIST_SCREEN)
    }

    val auth: () -> Unit = {
        navController.navigate(AUTH_SCREEN)
    }

//    val listSample: (String) -> Unit = { id ->
//        navController.navigate("list/${id}") {
////          popUpTo(route) ... routeで指定した画面まで戻る. inclusive: 指定した画面の一つ前まで戻る
//            popUpTo("list/${id}") {
//                inclusive = true
//            }
//        }
//    }
}