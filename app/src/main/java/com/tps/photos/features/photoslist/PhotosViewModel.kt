package com.tps.photos.features.photoslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tps.photos.network.PhotosNetworkService
import com.tps.photos.network.model.ImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val photoService: PhotosNetworkService,
) : ViewModel() {

    // StateFlow for photos - represents the list of photos in a mutable state
    private val _photos = MutableStateFlow<List<ImageResponse>>(emptyList())
    val photos: StateFlow<List<ImageResponse>> = _photos

    // StateFlow for loading state - represents whether data is being loaded
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // StateFlow for error - represents the error message in case of failure
    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val feedItems = photoService.getPhotoList()
                _photos.value = feedItems
                _isLoading.value = false
            } catch (error: Exception) {
                _photos.value = emptyList()
                _isLoading.value = false
                _error.value = "Failed to load photos: ${error.message}"
            }
        }
    }

    // Fetch details of a specific image by ID
    fun getImageDetail(imageId: Int): ImageResponse? {
        return _photos.value.find { it.id == imageId }
    }
}
