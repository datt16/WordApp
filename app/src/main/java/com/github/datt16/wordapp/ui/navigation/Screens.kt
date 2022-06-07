package com.github.datt16.wordapp.ui.navigation

import androidx.navigation.NavController

class Screens(navController: NavController) {
    val test1: () -> Unit = {
        navController.navigate("test1")
    }

    val test2: () -> Unit = {
        navController.navigate("auth")
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