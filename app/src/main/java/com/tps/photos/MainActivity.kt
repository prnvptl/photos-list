package com.tps.photos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tps.photos.features.photoslist.PhotosFragment

/**
 * The initial Activity for the app.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val photosFragment = PhotosFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container, photosFragment,
                PhotosFragment.TAG
            )
            .commit()
    }
}
