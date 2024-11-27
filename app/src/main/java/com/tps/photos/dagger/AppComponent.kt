package com.tps.photos.dagger

import com.tps.photos.TCApplication
import com.tps.photos.features.photoslist.PhotosFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: TCApplication)
    fun inject(photosFragment: PhotosFragment)

}
