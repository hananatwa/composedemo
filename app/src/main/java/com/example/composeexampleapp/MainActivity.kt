package com.example.composeexampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composeexampleapp.reposatory.daos.SuperHero
import com.example.composeexampleapp.viewModel.LocationsViewModel

class MainActivity : ComponentActivity() {

    private val locationViewModel: LocationsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterListScreen()

        }
    }
}


@Composable
fun CharacterListScreen(viewModel: LocationsViewModel = viewModel()) {
    val characters by viewModel.characters.observeAsState(emptyList())

    LazyColumn {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: SuperHero) {
    val imageUrl = "${character.thumbnail?.path ?: ""}.${character.thumbnail?.extension ?: ""}"

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg")
            .build()
    ).state
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        character.name?.let { Text(text = it) }
        character.description?.let { Text(text = it) }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(200.dp)
                .background(Color.Red)
                .clip(RoundedCornerShape(22.dp)),

            painter = rememberAsyncImagePainter(model = "http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg"),
            contentDescription = "movie.title",
            contentScale = ContentScale.Crop
        )

        if (imageState is AsyncImagePainter.State.Loading){
            Log.i("loading", "CharacterItem: ")

        }
        if (imageState is AsyncImagePainter.State.Success) {

           /* Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(200.dp)
                    .background(Color.Red)
                    .clip(RoundedCornerShape(22.dp)),

                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "movie.title",
                contentScale = ContentScale.Crop
            )*/
        }

        Log.i("Image22", "CharacterItem: $imageUrl")

    }
}


@Composable
fun MainCard(location: SuperHero) {
    Card(
        modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.maps),
                contentDescription = "Location image",
                Modifier
                    .size(60.dp)
                    .padding(8.dp),
                alignment = Alignment.BottomCenter
            )
            Column(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .height(IntrinsicSize.Max)
            ) {
                Text(
                    color = Color.Magenta,
                    text = " ${location.name}",
                    fontSize = 25.sp
                )
                Text(
                    color = Color.Gray,
                    text = " ${location.description}",
                    fontSize = 18.sp

                )
            }

        }
    }
}

/*@Composable
fun LocationsList(locations: List<MyLocation>) {
    LazyColumn {
        items(locations) { location ->
            MainCard(location)
        }
    }
}*/

@Composable
fun HeroesList(heroes: List<SuperHero>) {
    LazyColumn {
        items(heroes) { hero ->
            MainCard(hero)
        }
    }
}

/*@Preview
@Composable
fun ViewData() {

    MainCard(
        MyLocation(
            title = "Nhuh", description = "Susan Lai\n" +
                    "12th floor, Flat G\n" +
                    "Magnolia Building\n" +
                    "212 Sycamore Street\n" +
                    "WAN CHAI HONG KONG"
        )
    )
}*/
