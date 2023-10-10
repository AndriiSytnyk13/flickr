package com.sytyy.compose.practice.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("interesting_photo_table")
data class InterestingPhotoEntity(
    @PrimaryKey
    val photoId: Long,
    val photoUrl: String,
    val title: String,
    val userName: String,
    val realName: String,
    val description: String
)
