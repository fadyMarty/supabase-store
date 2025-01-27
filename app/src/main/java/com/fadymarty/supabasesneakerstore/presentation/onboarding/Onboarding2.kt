package com.fadymarty.supabasesneakerstore.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun Onboarding2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(Random.nextLong())),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Onboarding2"
        )
    }
}