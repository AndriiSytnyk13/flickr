package com.sytyy.compose.practice.data.remote

import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("_content")
    val titleName: String
)