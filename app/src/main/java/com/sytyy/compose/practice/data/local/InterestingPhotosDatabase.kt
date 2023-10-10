package com.sytyy.compose.practice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InterestingPhotoEntity::class], version = 1)
abstract class InterestingPhotosDatabase : RoomDatabase() {

    abstract fun interestingPhotosDao(): InterestingPhotosDao
}