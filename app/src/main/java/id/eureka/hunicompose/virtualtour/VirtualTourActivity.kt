package id.eureka.hunicompose.virtualtour

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.panoramagl.PLImage
import com.panoramagl.PLManager
import com.panoramagl.PLSphericalPanorama
import id.eureka.hunicompose.databinding.ActivityVirtualTourBinding
import kotlinx.coroutines.launch


class VirtualTourActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVirtualTourBinding
    private lateinit var plManager: PLManager
    private val viewModel: VirtualTourViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVirtualTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setImages()

        setNavigator()

        plManager = PLManager(this).apply {
            setContentView(binding.root)
            onCreate()
        }

        val panorama = PLSphericalPanorama()
//        panorama.camera.lookAt(30.0f, 90.0f)
        plManager.panorama = panorama

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.currentIndex.collect { index ->
                    Glide.with(this@VirtualTourActivity)
                        .asBitmap()
                        .load(viewModel.imageUrls.value[index])
                        .override(1024, 1024)
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?,
                            ) {
                                fadeInPanoramaGL(panorama)
                                panorama.setImage(PLImage(resource))
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {

                            }
                        })
                }
            }
        }
    }

    private fun setNavigator() {
        binding.virtualTourNavigator.setContent {
            VirtualTourNavigator(viewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        plManager.onResume()
    }

    override fun onPause() {
        plManager.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        plManager.onDestroy()
        super.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return plManager.onTouchEvent(event!!)
    }

    private fun fadeInPanoramaGL(panoramaGL: PLSphericalPanorama) {
        val fadeIn: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                panoramaGL.setAlpha(interpolatedTime)
            }
        }

        fadeIn.duration = 1000
        binding.root.startAnimation(fadeIn)
    }

}