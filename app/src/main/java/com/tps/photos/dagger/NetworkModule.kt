package com.tps.photos.dagger

import com.google.gson.GsonBuilder
import com.tps.photos.Constants
import com.tps.photos.network.PhotosNetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides Network communication related instances.
 */
@Module
class NetworkModule {
    @Provides
    fun providePhotosNetworkService(): PhotosNetworkService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.PHOTOS_BASE_URL)
            .build()
        return retrofit.create(PhotosNetworkService::class.java)
    }
}
