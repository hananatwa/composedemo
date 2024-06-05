package com.example.composeexampleapp.views

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composeexampleapp.model.dataclasses.SuperHero


@Composable
fun HeroItem(
    hero: SuperHero,
    onLongPress: (SuperHero) -> Unit
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(
                data = "${hero.thumbnail?.path ?: ""}.${hero.thumbnail?.extension ?: ""}".replace(
                    "http://",
                    "https://"
                )
            )
            .apply { crossfade(true) }
            .build()
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongPress(hero) }
                )
            },

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
       val modifier: Modifier =Modifier.Companion
           .padding(16.dp)
            .fillMaxWidth()

        HeroColumnData(hero, painter,modifier)
    }
}
