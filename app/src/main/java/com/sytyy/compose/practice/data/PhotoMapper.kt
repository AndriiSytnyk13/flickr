package com.sytyy.compose.practice.data

import com.sytyy.compose.practice.data.local.InterestingPhotoEntity
import com.sytyy.compose.practice.data.remote.PhotoRemoteData
import com.sytyy.compose.practice.domain.InterestingPhotoModel


fun PhotoRemoteData.mapToInterestingPhotoEntity() = InterestingPhotoEntity(
    photoId = id.toLong(),
    photoUrl = imageUrl(),
    title = title(),
    userName = owner.userName,
    realName = owner.realName,
    description = description()
)

fun InterestingPhotoEntity.mapToInterestingPhotoModel() = InterestingPhotoModel(
    photoId = photoId,
    photoUrl = photoUrl,
    title = title,
    userName = userName,
    realName = realName,
    description = description
)

