package com.tps.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.tps.photos.features.photoslist.PhotosViewModel
import com.tps.photos.network.PhotosNetworkService
import com.tps.photos.network.model.ImageResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxJavaRule = RxJavaRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    val mockPhotoService: PhotosNetworkService = mock()

    val viewModel = PhotosViewModel(mockPhotoService)

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testGetPhotosSuccess() = runTest {
        val tempImage = ImageResponse(
            filename = "0.jpeg",
            id = 0,
            author = "Alejandro Escamilla",
            authorUrl = "https://unsplash.com/photos/yC-Yzbqy7PY",
            postUrl = "https://unsplash.com/photos/yC-Yzbqy7PY",
            format = "jpeg",
            width = 5000,
            height = 3333
        )

        Mockito.`when`(mockPhotoService.getPhotoList()).then {
            listOf(tempImage,tempImage)
        }
        viewModel.getPhotos()
        val list = viewModel.photos.value
        assertEquals(2, list.size)
        assertEquals("0.jpeg", list[0].filename)
        assertEquals("Alejandro Escamilla", list[0]?.author)
    }
}
