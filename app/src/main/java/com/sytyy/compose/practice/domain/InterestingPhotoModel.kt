package com.sytyy.compose.practice.domain

data class InterestingPhotoModel(
    val photoId: Long,
    val photoUrl: String,
    val title: String,
    val userName: String,
    val realName: String,
    val description: String
)