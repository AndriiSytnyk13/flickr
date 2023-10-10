package com.sytyy.compose.practice.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sytyy.compose.practice.R
import com.sytyy.compose.practice.domain.InterestingPhotoModel
import com.sytyy.compose.practice.util.ListElement

@Composable
fun DetailScreen(photosViewModel: PhotosViewModel) {
    val interestingPhotoDetail = photosViewModel.interestingPhotoDetail
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .size(300.dp),
            model = interestingPhotoDetail?.photoUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        NavigationBar(photosViewModel)
        DetailInformationGrid(interestingPhotoDetail)
        Text(text = "Description:", modifier = Modifier.padding(top = 10.dp), fontSize = 20.sp)
        interestingPhotoDetail?.description?.let { description ->
            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun NavigationBar(photosViewModel: PhotosViewModel) {
    val listElement = photosViewModel.listElement
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        if (listElement == ListElement.FIRST_ELEMENT) {
            Spacer(modifier = Modifier.size(40.dp))
        } else {
            NavigationButton(
                imageVector = Icons.Rounded.ArrowBack, contentDescription = stringResource(
                    id = R.string.arrow_back_description
                )
            ) {
                photosViewModel.setPreviousItem()
            }
        }
        if (listElement == ListElement.LAST_ELEMENT) {
            Spacer(modifier = Modifier.size(40.dp))
        } else {
            NavigationButton(
                imageVector = Icons.Rounded.ArrowForward, contentDescription = stringResource(
                    id = R.string.arrow_forward_description
                )
            ) {
                photosViewModel.setNextItem()
            }
        }
    }
}

@Composable
private fun NavigationButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClickEvent: () -> Unit
) {
    IconButton(onClick = { onClickEvent() }, modifier = Modifier.size(40.dp)) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
private fun DetailInformationGrid(interestingPhotoDetail: InterestingPhotoModel?) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = 10.dp),
        columns = GridCells.Fixed(2),
        userScrollEnabled = false
    ) {
        item {
            DetailText("UserName", Color.Green)
        }
        item {
            DetailText(interestingPhotoDetail?.userName, Color.Green)
        }
        item {
            DetailText("Realname", Color.Blue)
        }
        item {
            DetailText(interestingPhotoDetail?.realName, Color.Blue)
        }
        item {
            DetailText("Title")
        }
        item {
            DetailText(interestingPhotoDetail?.title)
        }
    }
}


@Composable
private fun DetailText(text: String?, textColor: Color = Color.Red) {
    Text(
        text = text.toString(),
        textAlign = TextAlign.Start,
        fontSize = 18.sp,
        color = textColor,
    )
}
