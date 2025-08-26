package com.example.re_book.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.re_book.presentation.common.verticalGradientBackground
import com.example.re_book.presentation.navigations.DestinationScreen
import com.example.re_book.ui.theme.appBackGroundColor
import com.example.re_book.ui.theme.appContentColor
import com.example.re_book.ui.theme.appProgressColor
import com.example.re_book.ui.theme.appStatueBarColor
import com.example.re_book.ui.theme.endGradient
import com.example.re_book.ui.theme.startGradient
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailScreenUI(detailScreen: DestinationScreen.DetailScreen, navController: NavHostController) {
    Text(text = detailScreen.pdfUrl)
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(detailScreen.pdfUrl),
        isZoomEnable = true
    )

    val progressState = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(detailScreen.authorName, color = Color.White)
                },
                navigationIcon = {
                    Icon(
                        Icons.Filled.ArrowBack,
                        "null",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            navController.navigate(DestinationScreen.HomeScreen) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        })
                },
                backgroundColor = appStatueBarColor
            )
        },
        modifier = Modifier.fillMaxSize()

    ) { innerpadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalGradientBackground()
        ) {
            LaunchedEffect(pdfState.isLoaded) {
                delay(2000)
                progressState.value = true
                Log.d("debug", "Value Become  = ${pdfState.isLoaded}")
            }
            if (!progressState.value) {
                CircularProgressIndicator(color = appProgressColor, trackColor = appContentColor)
            } else if (progressState.value) {
                Log.d("debug", "Data Loaded  😊😊😊😊😊😊😊😊")
                VerticalPDFReader(
                    state = pdfState,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalGradientBackground()
                )
            }
        }
    }
}



