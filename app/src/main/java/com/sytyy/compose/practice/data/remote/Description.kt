package com.sytyy.compose.practice.data.remote

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("_content")
    val descriptionText: String
)