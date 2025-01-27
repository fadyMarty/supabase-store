package com.fadymarty.supabasesneakerstore.presentation.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val pagerState = rememberPagerState {
            3
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> "Onboarding1"
                    1 -> "Onboarding2"
                    2 -> "Onboarding3"
                    else -> ""
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            when (index) {
                0 -> Onboarding1()
                1 -> Onboarding2()
                2 -> Onboarding3()
                else -> Onboarding1()
            }
        }

        val scope = rememberCoroutineScope()

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.75f)
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            onClick = {
                scope.launch {
                    if (pagerState.currentPage == 2) {
                        viewModel.saveAppEntry()
                    } else {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(
                text = buttonState.value
            )
        }
    }
}