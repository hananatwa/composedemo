package com.example.composeexampleapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composeexampleapp.viewModel.SuperHeroDetailsViewModel

@Composable
fun SuperHeroDetailsScreen( viewModel: SuperHeroDetailsViewModel = viewModel()) {
    val character by viewModel.character.observeAsState()


    character?.let {
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
                        .size(220.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                it.name?.let { Text(text = it , fontWeight = FontWeight.Bold, fontSize = 24.sp) }
                Spacer(modifier = Modifier.height(8.dp))
                it.description?.let { Text(text = it,fontWeight = FontWeight.Medium, fontSize = 20.sp) }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Modified: ${it.modified}")
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(text = "Comics",fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
            it.comics?.items?.let { it1 ->
                items(it1) { comic ->
                    comic.name?.let { it2 -> Text(text = it2) }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Series",fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
            it.series?.let { it1 ->
                items(it1.items) { series ->
                    series.name?.let { it2 -> Text(text = it2) }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    } ?: run {
        Text(text = "Loading...")
    }
}
