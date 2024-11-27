package com.tps.photos.network.model

import com.google.gson.annotations.SerializedName

/**
 * Store image data model.
 */
data class ImageResponse(
    @SerializedName("filename")
    val filename: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("author")
    val author: String,

    @SerializedName("author_url")
    val authorUrl: String,

    @SerializedName("post_url")
    val postUrl: String,

    @SerializedName("format")
    val format: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int
)
