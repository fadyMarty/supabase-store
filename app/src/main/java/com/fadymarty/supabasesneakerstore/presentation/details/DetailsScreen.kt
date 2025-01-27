package com.fadymarty.supabasesneakerstore.presentation.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.getSneakersById(id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        state.sneakers?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEAEEEF))
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Box {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.sneakers.imageUrl)
                            .build(),
                        contentDescription = null
                    )

                    IconButton(
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(top = 8.dp)
                            .align(Alignment.TopEnd),
                        onClick = {
                            viewModel.addSneakersToFavorites()
                        }
                    ) {
                        Icon(
                            imageVector = if (state.sneakers.inFavorites) {
                                Icons.Rounded.Favorite
                            } else {
                                Icons.Rounded.FavoriteBorder
                            },
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }

                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart),
                        text = state.sneakers.name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${state.sneakers.price}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = state.sneakers.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(0.75f),
                    text = state.sneakers.description,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    color = Color.Black
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