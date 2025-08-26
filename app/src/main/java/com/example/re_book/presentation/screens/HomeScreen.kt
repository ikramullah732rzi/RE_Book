package com.example.re_book.presentation.screens

/*
@Composable
fun HomeScreenUI() {

}
*/


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.re_book.presentation.common.verticalGradientBackground
import com.example.re_book.presentation.navigations.DestinationScreen
import com.example.re_book.presentation.viewmodels.EBookViewModel
import com.example.re_book.ui.theme.appBackGroundColor
import com.example.re_book.ui.theme.appContentColor
import com.example.re_book.ui.theme.appProgressColor
import com.example.re_book.ui.theme.appStatueBarColor
import com.example.re_book.ui.theme.endGradient
import com.example.re_book.ui.theme.startGradient
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun HomeScreenUI(navController: NavController,eBookViewModel: EBookViewModel = hiltViewModel()) {
    val tabs = listOf("All Books", "Category")
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        eBookViewModel.getAllBook()
    }
    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = appStatueBarColor,
                contentColor = appContentColor,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .verticalGradientBackground()
                .padding(paddingValues)
        ) { page ->

            when (page) {
                0 -> HomeTabUI(navController = navController, eBookViewModel = eBookViewModel)
                1 -> CategoryTabUI(navController = navController, eBookViewModel = eBookViewModel)
            }
        }
    }
}


@Composable
fun HomeTabUI(eBookViewModel: EBookViewModel, navController: NavController) {

    val state = eBookViewModel.getAllBookState.collectAsStateWithLifecycle().value
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.isLoading) {
            CircularProgressIndicator(color = appProgressColor, trackColor = appContentColor)
        } else if (state.error != null) {
            Text(
                text = state.error,
                modifier = Modifier.padding(16.dp)
            )
        } else if (state.model.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.model) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    DestinationScreen.DetailScreen(
                                        it.bookData.toString(),
                                        authorName = it.bookName.toString()
                                    )
                                )
                            }
                            .padding(5.dp),
                        backgroundColor = appStatueBarColor
                      ){
                        Text(
                            text = "🏠 ${it.bookName}",
                            modifier = Modifier.padding(18.dp),
                            color = appContentColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }


}
