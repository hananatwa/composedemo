package com.example.composeexampleapp.views.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.layout.offset
import androidx.compose.material.FractionalThreshold
import androidx.compose.material3.CardDefaults
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeexampleapp.model.dataclasses.SuperHero
import com.example.composeexampleapp.views.HeroColumnData


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableHeroItem(
    hero: SuperHero,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onHeroClick: (Int) -> Unit,
) {
    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { 200.dp.toPx() }
    val anchors = mapOf(-sizePx to -1, 0f to 0, sizePx to 1)

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = "${hero.thumbnail?.path ?: ""}.${hero.thumbnail?.extension ?: ""}".replace("http://", "https://"))
            .apply { crossfade(true) }
            .build()
    )
    LaunchedEffect(swipeableState) {
        snapshotFlow { swipeableState.currentValue }
            .collect { currentValue ->
                when (currentValue) {
                    1 -> {
                        onSwipeRight()
                    }

                    -1 -> onSwipeLeft()
                }
                swipeableState.snapTo(0)
            }
    }

    val backgroundColor by animateColorAsState(
        targetValue = when {
            swipeableState.offset.value > 0 -> Color.Red
            swipeableState.offset.value < 0 -> Color.Green
            else -> Color.Transparent
        }, label = ""
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { hero.id?.let { onHeroClick(it) } }
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(.5f) },
                orientation = Orientation.Horizontal
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        val modifier: Modifier =Modifier.Companion
            .padding(16.dp)
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .fillMaxWidth()
            .background(backgroundColor)
        HeroColumnData(hero, painter,modifier)
    }
}
