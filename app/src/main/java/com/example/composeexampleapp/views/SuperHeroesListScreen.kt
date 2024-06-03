package com.example.composeexampleapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composeexampleapp.reposatory.daos.SuperHero
import com.example.composeexampleapp.viewModel.SuperHeroesViewModel


@Composable
fun SuperHeroesListScreen(
    navController: NavHostController,
    viewModel: SuperHeroesViewModel = viewModel()
) {
    val characters by viewModel.characters.observeAsState(emptyList())

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(characters) { hero ->
            HeroItem(hero) {
                hero.id?.let { id ->
                    run {
                        navController.navigate(
                            Screen.HomeDetails.createRoute(
                                id
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeroItem(hero: SuperHero, onHeroClick: (Int) -> Unit) {
    val imageUrl =
        "${hero.thumbnail?.path ?: ""}.${hero.thumbnail?.extension ?: ""}".replace(
            "http://",
            "https://"
        )
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build()
    )


    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { hero.id?.let { onHeroClick(it) } },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            hero.name?.let {
                Text(
                    text = it,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            hero.description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    maxLines = 2,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Image(
                painter = painter,
                contentDescription = hero.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(25.dp))
            )


        }
    }
}
