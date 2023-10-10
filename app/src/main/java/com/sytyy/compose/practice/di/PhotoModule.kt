package com.sytyy.compose.practice.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sytyy.compose.practice.R
import com.sytyy.compose.practice.data.local.InterestingPhotosDao
import com.sytyy.compose.practice.data.local.InterestingPhotosDatabase
import com.sytyy.compose.practice.data.remote.InterestingPhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    @Singleton
    fun provideInterestingPhotosService(retrofit: Retrofit): InterestingPhotosService =
        retrofit.create(InterestingPhotosService::class.java)

    @Provides
    @Singleton
    fun provideInterestingPhotosDatabase(@ApplicationContext context: Context): InterestingPhotosDatabase =
        Room.databaseBuilder(
            context,
            InterestingPhotosDatabase::class.java,
            context.getString(R.string.database_name)
        ).build()

    @Provides
    @Singleton
    fun provideInterestingPhotosDao(interestingPhotosDatabase: InterestingPhotosDatabase): InterestingPhotosDao =
        interestingPhotosDatabase.interestingPhotosDao()

}