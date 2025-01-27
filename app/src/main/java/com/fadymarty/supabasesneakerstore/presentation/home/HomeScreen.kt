package com.fadymarty.supabasesneakerstore.presentation.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fadymarty.supabasesneakerstore.presentation.components.SneakersItem

@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = innerPadding.calculateTopPadding(),
                end = 8.dp,
                bottom = innerPadding.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(state.sneakersList) { index, item ->
                SneakersItem(
                    sneakers = item,
                    navController = navController
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Failed to load sneakers",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                strokeCap = StrokeCap.Round,
                trackColor = Color.DarkGray
            )
        }
    }
}