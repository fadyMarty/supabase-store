package com.fadymarty.supabasesneakerstore.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.fadymarty.supabasesneakerstore.domain.model.Sneakers
import com.fadymarty.supabasesneakerstore.presentation.navigation.Screen

@Composable
fun SneakersItem(
    sneakers: Sneakers,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAEEEF)
            ),
            onClick = {
                navController.navigate(Screen.Details.route + "/${sneakers.id}")
            },
            shape = RoundedCornerShape(20.dp)
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(sneakers.imageUrl)
                        .build(),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 8.dp),
                    text = sneakers.id.toString(),
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp),
                    text = "$${sneakers.price}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 4.dp),
                text = sneakers.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2,
                color = Color.Black
            )
        }
    }
}