package com.tps.photos.features.photoslist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PhotosNavigation(viewModel: PhotosViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            PhotosList(viewModel, navController)
        }
        composable("detail/{imageId}") { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")?.toInt() ?: 0
            PhotoDetail(imageId = imageId, navController = navController, viewModel = viewModel)
        }
    }
}
