package com.tps.photos.features.photoslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import com.tps.photos.TCApplication
import com.tps.photos.ViewModelFactory
import javax.inject.Inject

/**
 * Displays the list of Stores with its title, description and the cover image to the user.
 */
class PhotosFragment : Fragment() {
    companion object {
        const val TAG = "PhotosFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PhotosViewModel>

    private val viewModel: PhotosViewModel by lazy {
        viewModelFactory.get<PhotosViewModel>(
            requireActivity()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TCApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getPhotos()
        return ComposeView(requireContext()).apply {
            setContent {
                // Initialize the NavController in the Composable scope
                val navController = rememberNavController()
                PhotosNavigation(viewModel, navController)
            }
        }
    }
}
