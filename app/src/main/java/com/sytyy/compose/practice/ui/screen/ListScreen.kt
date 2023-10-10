package com.sytyy.compose.practice.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.sytyy.compose.practice.ui.navigation.navigateFromListScreenToDetailScreen


@Composable
fun ListScreen(
    navHostController: NavHostController,
    photosViewModel: PhotosViewModel
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        columns = GridCells.Fixed(3)
    ) {
        items(photosViewModel.interestingPhotos, key = {
            it.photoId
        }) { photoModel ->
            ListItem(photoModel.photoUrl, photoModel.title) {
                photosViewModel.setCurrentPhotoId(photoModel.photoId)
                navHostController.navigateFromListScreenToDetailScreen()
            }
        }
    }

}

@Composable
fun ListItem(photoUrl: String, title: String, onClickEvent: () -> Unit) {
    IconButton(
        onClick = { onClickEvent() }, modifier = Modifier
            .padding(5.dp)
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = photoUrl,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {
                    CircularProgressIndicator(modifier = Modifier.size(10.dp))
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 15.sp,
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
            )
        }
    }
}
