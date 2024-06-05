package com.example.composeexampleapp.views.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composeexampleapp.viewModel.SuperHeroDetailsViewModel

@Composable
fun SuperHeroDetailsScreen(viewModel: SuperHeroDetailsViewModel = viewModel()) {
    val heroesDetailsScreenState by viewModel.heroesDetailsScreenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchSuperHero()
    }

    if (heroesDetailsScreenState.loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    heroesDetailsScreenState.hero?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "${it.thumbnail?.path}.${it.thumbnail?.extension}".replace(
                            "http://",
                            "https://"
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )

                it.name?.let { Text(text = it, fontWeight = FontWeight.Bold, fontSize = 30.sp) }
                Spacer(modifier = Modifier.height(10.dp))
                it.description?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Modified:", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "${it.modified}", fontSize = 20.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(text = "Comics", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                it.comics?.items?.let { it1 ->
                    Text(text = it1.size.toString(), fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Series", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                it.series?.items?.let { it1 ->
                    Text(text = it1.size.toString(), fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }

}

