package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.model.FrogPhoto
import androidx.compose.foundation.lazy.grid.items

@Composable
fun FrogCardComposable(frog: FrogPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        val header = "${frog.name} (${frog.type})"
        Text(text = header)
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(frog.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.frog_photo),
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
        Text(text = frog.description)
    }
}

@Composable
fun PhotosGridScreen(
    photos: List<FrogPhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(items = photos, key = {photo -> photo.name}) {
            photo -> FrogCardComposable(
                frog = photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f))
        }
    }
}

@Composable
fun HomeScreen(
    frogUIState: FrogUIState,
    modifier: Modifier = Modifier
) {
    PhotosGridScreen(
        photos = frogUIState.photos, modifier = modifier
    )
}
