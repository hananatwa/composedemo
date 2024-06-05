package com.example.composeexampleapp.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeexampleapp.model.dataclasses.SuperHero
@Composable
fun HeroesScreen(
    navController: NavHostController,
    heroes: List<SuperHero>,
    emptyMessage: String,
    emptyImageRes: Int,
    onLongPress: (SuperHero) -> Unit
) {

    val heroesState by rememberUpdatedState(newValue = heroes)
    var showDialog by remember { mutableStateOf(false) }
    var selectedHero by remember { mutableStateOf<SuperHero?>(null) }

    if (showDialog && selectedHero != null) {
        ConfirmationDialog(
            message = "Remove this Hero ?",
            onConfirm = {
                onLongPress(selectedHero!!)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    if (heroesState.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = emptyImageRes),
                contentDescription = "empty state icon",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Text(text = emptyMessage, style = MaterialTheme.typography.headlineMedium)
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(heroesState) { hero ->
                HeroItem(hero = hero, onLongPress = {
                    selectedHero = hero
                    showDialog = true
                })
            }
        }
    }
}








