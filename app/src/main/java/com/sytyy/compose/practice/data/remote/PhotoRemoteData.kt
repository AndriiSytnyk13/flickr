package com.sytyy.compose.practice.data.remote

data class PhotoRemoteData(
    val id: String,
    private val title: Title,
    private val secret: String,
    private val farm: String,
    private val server: String,
    private val description: Description,
    val owner: Owner,

    ) {

    fun title() = title.titleName.ifBlank { "Title" }

    fun description() = description.descriptionText.ifBlank { "..." }

    fun imageUrl(): String = "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"


}