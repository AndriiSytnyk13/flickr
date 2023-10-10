package com.sytyy.compose.practice.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface InterestingPhotosDao {

    @Upsert
    suspend fun upsertInterestingPhoto(interestingPhotos: InterestingPhotoEntity)

    @Query("SELECT * FROM interesting_photo_table")
    suspend fun getInterestingPhotos(): List<InterestingPhotoEntity>

    @Query("SELECT * FROM interesting_photo_table WHERE photoId = :photoId")
    suspend fun getInterestingPhotoDetail(photoId: Long): InterestingPhotoEntity


}