package com.tps.photos.features.photoslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoDetail(imageId: Int, navController: NavController, viewModel: PhotosViewModel) {
    // Fetch the image details from the ViewModel
    val image = viewModel.getImageDetail(imageId)

    // Ensure image is loaded
    image?.let {
        val imageUrl = "https://picsum.photos/${it.width}/${it.height}?image=${it.id}"

        // Determine if the image is landscape or portrait
        val isLandscape = it.width > it.height

        // Column for layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Back Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Back")
            }

            // Use BoxWithConstraints to get the screen width and height
            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Calculate the height limit for portrait images (e.g., max height = 80% of screen height)
                val maxHeight = if (!isLandscape) maxHeight * 0.8f else maxHeight

                // Display image based on aspect ratio (landscape or portrait)
                GlideImage(
                    model = imageUrl,
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxHeight) // Limit height for portrait images
                        .padding(8.dp), // Some padding around the image
                    contentScale = ContentScale.Fit // Preserve aspect ratio
                )
            }

            // Display the author's name directly below the image
            Text(
                text = "Author: ${it.author}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), // Directly below the image
                color = Color.Black
            )
        }
    } ?: run {
        // If the image data is not available, show a loading/error message
        Text(text = "Loading image...")
    }
}
