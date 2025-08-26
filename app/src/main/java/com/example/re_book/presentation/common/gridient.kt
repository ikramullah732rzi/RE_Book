package com.example.re_book.presentation.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.re_book.ui.theme.endGradient
import com.example.re_book.ui.theme.startGradient

fun Modifier.verticalGradientBackground(
): Modifier {
    return this
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(startGradient, endGradient)
            )
        )
}
