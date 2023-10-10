package com.sytyy.compose.practice.ui.screen

import com.sytyy.compose.practice.data.repository.PhotosRepositoryImpl
import com.sytyy.compose.practice.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosRepositoryImpl: PhotosRepositoryImpl) :
    BaseViewModel(photosRepositoryImpl) {

    init {
        getInterestingPhotos()
    }
}