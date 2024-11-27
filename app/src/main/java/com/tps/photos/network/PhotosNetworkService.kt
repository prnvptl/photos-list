package com.tps.photos.network

import com.tps.photos.network.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosNetworkService {

    @GET("list")
    suspend fun getPhotoList(): List<ImageResponse>


    @GET("{width}/{height}?image={id}")
    suspend fun getStoreDetails(
        @Path("id") imageId: String,
        @Path("width") width: Int,
        @Path("height") height: Int,
    ): ImageResponse
}
