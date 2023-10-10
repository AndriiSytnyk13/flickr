package com.sytyy.compose.practice.data.remote

import com.sytyy.compose.practice.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface InterestingPhotosService {


    @GET("rest/?api_key=${BuildConfig.API_KEY}&method=flickr.interestingness.getList&format=json&nojsoncallback=1")
    suspend fun getInterestingPhotos(): InterestingPhotosResponse

    @GET("rest/?method=flickr.photos.getInfo&api_key=${BuildConfig.API_KEY}&format=json&nojsoncallback=1")
    suspend fun getPhotoDetailById(
        @Query("photo_id") id: String,
        @Query("secret") secret: String
    ): PhotoResponse
}
