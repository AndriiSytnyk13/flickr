package com.sytyy.compose.practice.di

import com.sytyy.compose.practice.data.repository.PhotosRepositoryImpl
import com.sytyy.compose.practice.domain.InterestingPhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotoRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPhotoRepository(photosRepositoryImpl: PhotosRepositoryImpl): InterestingPhotoRepository
}