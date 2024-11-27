package com.tps.photos.features.photoslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PhotosList(viewModel: PhotosViewModel, navController: NavController) {
    val list = viewModel.photos.collectAsState().value

    LazyColumn(modifier = Modifier.padding(top = 4.dp)) {
        items(list) { image ->
            // Handle item click to navigate to the detail view
            Surface(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController.navigate("detail/${image.id}")
                }) {
                Text(text = image.filename, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
