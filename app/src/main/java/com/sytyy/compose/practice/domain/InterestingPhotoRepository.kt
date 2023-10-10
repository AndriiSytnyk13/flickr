package com.sytyy.compose.practice.domain

import kotlinx.coroutines.flow.Flow

interface InterestingPhotoRepository {

    suspend fun getInterestingPhotos(): Flow<List<InterestingPhotoModel>>

    suspend fun getInterestingPhotoDetail(photoID: Long): InterestingPhotoModel

}