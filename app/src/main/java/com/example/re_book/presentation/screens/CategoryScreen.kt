package com.example.re_book.presentation.screens

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.re_book.presentation.navigations.DestinationScreen
import com.example.re_book.presentation.viewmodels.EBookViewModel
import com.example.re_book.ui.theme.appContentColor
import com.example.re_book.ui.theme.appProgressColor
import com.example.re_book.ui.theme.appStatueBarColor


@Composable
fun CategoryTabUI(eBookViewModel: EBookViewModel, navController: NavController) {
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
                            text = "🏠 ${it.bookCategory}",
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
