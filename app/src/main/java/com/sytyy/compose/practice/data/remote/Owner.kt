package com.sytyy.compose.practice.data.remote

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("realname")
    val realName: String,
    @SerializedName("username")
    val userName: String
)