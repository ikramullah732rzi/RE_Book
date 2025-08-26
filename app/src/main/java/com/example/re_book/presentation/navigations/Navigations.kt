package com.example.re_book.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.re_book.presentation.screens.DetailScreenUI
import com.example.re_book.presentation.screens.HomeScreenUI
import kotlinx.serialization.Serializable


@Composable
fun App() {

    val navController = rememberNavController()
    NavHost(navController, startDestination = DestinationScreen.HomeScreen) {
        composable<DestinationScreen.HomeScreen> {
            HomeScreenUI(navController)
        }
        composable<DestinationScreen.CategoryScreen> {
            //CateGoryScreenUI()
        }
        composable<DestinationScreen.DetailScreen> {

            val detailScreen = it.toRoute<DestinationScreen.DetailScreen>()
            DetailScreenUI(detailScreen, navController)


        }

    }
}


@Serializable
sealed class DestinationScreen() {


    @Serializable
    data object HomeScreen : DestinationScreen()

    @Serializable
    data object CategoryScreen : DestinationScreen()

    @Serializable
    data class DetailScreen(val pdfUrl: String, val authorName: String) : DestinationScreen()
}