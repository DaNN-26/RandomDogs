package com.example.dogs.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dogs.R

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = DogsViewModel(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val dogImages = viewModel.dogImages.collectAsState()
    Scaffold(
        topBar = { DogsTopBar(onIconClick = { viewModel.refreshDogsPictures() }) }
    ) {
        DogsGrid(images = dogImages.value, paddingValues = it, modifier = modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsTopBar(
    onIconClick: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = "Random Dogs",
            fontWeight = FontWeight.Light
        ) },
        navigationIcon = {
            IconButton(onClick = { onIconClick() }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier.shadow(6.dp))
}

@Composable
fun DogsGrid(
    images: List<String>,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.padding(paddingValues)
    ) {
        items(images) {
            DogImage(image = it)
        }
    }
}

@Composable
fun DogImage(
    image: String
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image)
                .crossfade(true)
                .placeholder(R.drawable.ic_launcher_foreground)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .size(120.dp)
                .padding(6.dp)
                .shadow(elevation = 8.dp)
        )
    }
}