package com.sytyy.compose.practice.data.repository

import com.sytyy.compose.practice.data.local.InterestingPhotosDao
import com.sytyy.compose.practice.data.mapToInterestingPhotoEntity
import com.sytyy.compose.practice.data.mapToInterestingPhotoModel
import com.sytyy.compose.practice.data.remote.InterestingPhotosService
import com.sytyy.compose.practice.domain.InterestingPhotoModel
import com.sytyy.compose.practice.domain.InterestingPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val photosService: InterestingPhotosService,
    private val photosDao: InterestingPhotosDao
) : InterestingPhotoRepository {


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getInterestingPhotos(): Flow<List<InterestingPhotoModel>> = flow {
        val localPhotos = photosDao.getInterestingPhotos()
            .map { photoEntity ->
                photoEntity.mapToInterestingPhotoModel()
            }
        emit(localPhotos)
        val photoList = photosService.getInterestingPhotos().photos.photo
        val remotePhotos =
            photoList.asFlow().flatMapMerge(photoList.size) { photoResponse ->
                flow {
                    val photoDetail =
                        photosService.getPhotoDetailById(photoResponse.id, photoResponse.secret)
                    val photoEntity = photoDetail.photo.mapToInterestingPhotoEntity()
                    photosDao.upsertInterestingPhoto(photoEntity)
//                    photosDao.deleteInterestingPhoto(photoEntity)
                    val interestingPhotoModel = photoEntity.mapToInterestingPhotoModel()
                    emit(interestingPhotoModel)
                }
                    .flowOn(Dispatchers.IO)
            }.toList().sortedBy { it.photoId }
        if (localPhotos != remotePhotos) {
            emit(remotePhotos)
        }
    }.retry { exception ->
        (exception is Exception).also { getInterestingPhotos() }
    }.flowOn(Dispatchers.IO)

    override suspend fun getInterestingPhotoDetail(photoID: Long): InterestingPhotoModel =
        photosDao.getInterestingPhotoDetail(photoID).mapToInterestingPhotoModel()

}